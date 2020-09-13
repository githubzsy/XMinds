package com.zhoushiya.springDemo.demo2.bean;

/**
 * @author zhoushiya
 * @date 2020/9/13 10:38
 */
public class Order {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        System.out.println("执行Set方法设置属性值");
    }

    public Order() {
        System.out.println("执行无参构造函数");
    }

    public void initMethod(){
        System.out.println("执行initMethod");
    }

    public void destroyMethod(){
        System.out.println("执行destroyMethod");
    }
}
