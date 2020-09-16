package com.zhoushiya.springDemo.demo5.service;

/**
 * @author zhoushiya
 * @date 2020/9/15 11:02
 */
public interface IAccountService {
    /**
     * 转账
     * @param from 转出账户
     * @param to 转入账户
     * @param money 转多少钱
     */
    void transfer(int from,int to,float money);
}
