package com.zhoushiya.springDemo.demo5.dao;

/**
 * @author zhoushiya
 * @date 2020/9/15 11:01
 */
public interface IAccountDao {

    /**
     * 增加金额
     * @param id 账户id
     * @param money 金额
     */
    void increaseMoney(int id, float money);
}
