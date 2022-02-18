package club.codeqi.Dao.impl;

import club.codeqi.Annotation.Autowire;
import club.codeqi.Annotation.Respository;
import club.codeqi.Annotation.Service;
import club.codeqi.Dao.AccountDao;
import club.codeqi.pojo.Account;
import club.codeqi.utils.ConnectionUtils;
import club.codeqi.utils.DruidUtils;
import club.codeqi.utils.TransationManager;
import com.alibaba.druid.pool.DruidDataSource;

import java.lang.reflect.Field;
import java.sql.*;

@Respository
public class AccountDaoImpl implements AccountDao {

    @Autowire
    private ConnectionUtils connectionUtils;

    @Override
    public Account getAccount(String cardNo) throws Exception {
        Connection connection = connectionUtils.getCurrentConnection();
        String sql = "select * from account where cardNo = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setObject(1,cardNo);
        ResultSet resultSet = preparedStatement.executeQuery();
        Account account = new Account();
        while (resultSet.next()){
            Field[] declaredFields = account.getClass().getDeclaredFields();
            for(Field field:declaredFields){
                field.setAccessible(true);
                field.set(account,resultSet.getObject(field.getName()));
            }
        }
        return account;
    }

    @Override
    public int updateAccount(Account account) throws Exception {
        Connection connection = connectionUtils.getCurrentConnection();
        String sql = "update account set money = ? where cardNo = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setObject(1,account.getMoney());
        preparedStatement.setObject(2,account.getCardNo());
        preparedStatement.execute();
        return 1;
    }
}
