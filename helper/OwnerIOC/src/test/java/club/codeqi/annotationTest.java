package club.codeqi;

import club.codeqi.Annotation.Autowire;
import club.codeqi.Dao.AccountDao;
import club.codeqi.Dao.impl.AccountDaoImpl;
import club.codeqi.Service.AccountService;
import club.codeqi.Service.impl.AccountServiceImpl;
import club.codeqi.factory.beanFactorybyAnno;
import club.codeqi.pojo.Account;
import club.codeqi.utils.ConnectionUtils;
import org.junit.Test;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.util.Map;
import java.util.Set;

/**
 * @author codeqi
 * @version 1.0
 * @date 2021/2/7 0007 14:13
 */
public class annotationTest {
    @Test
    public void tsetinitIOC() throws Exception {
        Map<String, Object> map = beanFactorybyAnno.getMap();
        Set<Map.Entry<String, Object>> entries = map.entrySet();
        for(Map.Entry<String, Object> entry:entries){
            System.out.println(entry.getKey()+"----"+entry.getValue());
        }
        AccountDao accountDao = (AccountDao) beanFactorybyAnno.getObject("accountDao");
        System.out.println(accountDao.getAccount("6029621011001"));
        AccountService accountService = (AccountService) beanFactorybyAnno.getObject("accountService");
        accountService.transfer("6029621011000","6029621011001",100);
    }


    @Test
    public void getFieldclass(){
        Field[] declaredFields = AccountServiceImpl.class.getDeclaredFields();
        for(Field field : declaredFields){
            field.setAccessible(true);
            Class<?> type = field.getType();
            System.out.println(type.getName());
            AccountDao accountDao = new AccountDaoImpl();
            System.out.println(type.isInstance(accountDao));
        }

    }
}
