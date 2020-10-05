package com.zhoushiya.springcloud.alibaba.account.service;

import java.math.BigDecimal;

/**
 * @author zhoushiya
 * @date 2020/10/5 19:01
 */
public interface AccountService {
    /**
     * 扣减账户金额
     * @param userId 用户Id
     * @param money 金额数目
     */
    void decrease(long userId, BigDecimal money);
}
