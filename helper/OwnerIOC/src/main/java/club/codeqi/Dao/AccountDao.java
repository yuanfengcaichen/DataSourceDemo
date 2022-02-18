package club.codeqi.Dao;

import club.codeqi.pojo.Account;

import java.sql.SQLException;

public interface AccountDao {
    public Account getAccount(String cardNo) throws Exception;
    public int updateAccount(Account account) throws Exception;
}
