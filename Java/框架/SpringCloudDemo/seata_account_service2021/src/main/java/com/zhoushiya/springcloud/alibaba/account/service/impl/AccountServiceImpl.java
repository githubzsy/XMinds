package com.zhoushiya.springcloud.alibaba.account.service.impl;

import com.zhoushiya.springcloud.alibaba.account.dao.AccountDao;
import com.zhoushiya.springcloud.alibaba.account.service.AccountService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * @author zhoushiya
 * @date 2020/10/5 19:02
 */
@Service
public class AccountServiceImpl implements AccountService {

    private final AccountDao accountDao;

    public AccountServiceImpl(AccountDao accountDao) {
        this.accountDao = accountDao;
    }

    /**
     * 扣减账户金额
     *
     * @param userId 用户Id
     * @param money  金额数目
     */
    @Override
    public void decrease(long userId, BigDecimal money) {
        accountDao.decrease(userId,money);
    }
}
