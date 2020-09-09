package com.zhoushiya.interview.serviceLoaderDemo;

public class MyServiceLoaderImpl1 implements IMyServiceLoader {
    static {
        System.out.println("clinit: MyServiceLoaderImpl1");
    }

    @Override
    public String sayHello() {
        return "hello1";
    }

    @Override
    public String getName() {
        return "name1";
    }
}