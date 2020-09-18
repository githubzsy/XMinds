package com.zhoushiya.springDemo.demo3.dao;

import org.springframework.stereotype.Repository;

/**
 * @author zhoushiya
 * @date 2020/9/13 17:14
 */
@Repository
public class UserDao2 implements IUserDao {
    @Override
    public void add() {
        System.out.println("userDao2 add...");
    }
}
