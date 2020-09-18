package com.zhoushiya.interview.proxyDemo;

/**
 * 被代理类
 */
public class Hello implements HelloInterface{
    @Override
    public void sayHello() {
        System.out.println("Hello!");
    }
}