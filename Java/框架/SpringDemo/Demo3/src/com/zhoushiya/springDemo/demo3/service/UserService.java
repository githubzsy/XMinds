package com.zhoushiya.springDemo.demo3.service;

import com.zhoushiya.springDemo.demo3.dao.IUserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author zhoushiya
 * @date 2020/9/13 14:00
 */
@Service
public class UserService implements IUserService {

    //根据类型注入
    @Autowired
    //根据名称注入
    @Qualifier("userDao2")
    IUserDao userDao2;

    @Resource
    IUserDao userDao;

    @Value("abc")
    private String name;

    @Override
    public void add() {
        System.out.println("userService add...");
        userDao.add();
        userDao2.add();
        System.out.println(name);
    }
}
