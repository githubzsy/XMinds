package com.zhoushiya.springDemo.demo1;

/**
 * @author zhoushiya
 * @date 2020/9/11 11:16
 */
public class User {
    static {
        System.out.println("加载user");
    }

    public User() {
        System.out.println("实例化user");
    }

    public void add() {
        System.out.println("add");
    }

    String userName;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
