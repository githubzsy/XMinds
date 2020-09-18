package com.zhoushiya.springDemo.demo1;

/**
 * @author zhoushiya
 * @date 2020/9/11 16:25
 */
public class Order {
    private String name;
    private String address;

    public Order(String name, String address) {
        this.name = name;
        this.address = address;
    }

    @Override
    public String toString() {
        return "Order{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
