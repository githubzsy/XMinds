package com.zhoushiya.springDemo.demo5.service;

import com.zhoushiya.springDemo.demo5.dao.IAccountDao;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author zhoushiya
 * @date 2020/9/15 11:02
 */
@Service
public class AccountService implements IAccountService {
    private final IAccountDao accountDao;

    public AccountService(IAccountDao accountDao) {
        this.accountDao = accountDao;
    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ,timeout = 10)
    public void transfer(int from, int to, float money) {
        accountDao.increaseMoney(from, -money);
        accountDao.increaseMoney(to, money);
    }
}
