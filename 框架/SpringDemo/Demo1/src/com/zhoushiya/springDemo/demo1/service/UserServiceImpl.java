package com.zhoushiya.springDemo.demo1.service;

import com.zhoushiya.springDemo.demo1.dao.UserDao;

/**
 * @author zhoushiya
 * @date 2020/9/11 17:08
 */
public class UserServiceImpl implements UserService {

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    UserDao userDao;

    public void add(){
        System.out.println("service add");
        userDao.update();
    }
}
