package club.codeqi.Service.impl;

import club.codeqi.Annotation.Autowire;
import club.codeqi.Annotation.Service;
import club.codeqi.Annotation.Transactional;
import club.codeqi.Dao.AccountDao;
import club.codeqi.Dao.impl.AccountDaoImpl;
import club.codeqi.Service.AccountService;
import club.codeqi.pojo.Account;
import club.codeqi.utils.TransationManager;

@Service
@Transactional
public class AccountServiceImpl implements AccountService {

    @Autowire
    private AccountDao accountDao;

    @Override
    public int transfer(String from, String to, Integer money) throws Exception {

        Account fromAccount = accountDao.getAccount(from);
        Account toAccount = accountDao.getAccount(to);

        fromAccount.setMoney(fromAccount.getMoney()-money);
        toAccount.setMoney(toAccount.getMoney()+money);

        accountDao.updateAccount(fromAccount);
        int c=1/0;
        accountDao.updateAccount(toAccount);
        return 1;
    }
}
