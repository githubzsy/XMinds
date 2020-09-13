package com.zhoushiya.springDemo.demo3.service;

import org.springframework.stereotype.Service;

/**
 * @author zhoushiya
 * @date 2020/9/13 14:00
 */
@Service
public class UserService implements IUserService {
    @Override
    public String hello() {
        return "hello";
    }
}
