package com.zhoushiya.springDemo.demo3.dao;

import org.springframework.stereotype.Repository;

/**
 * @author zhoushiya
 * @date 2020/9/13 17:05
 */
@Repository
public class UserDao implements IUserDao {
    @Override
    public void add() {
        System.out.println("userDao add...");
    }
}
