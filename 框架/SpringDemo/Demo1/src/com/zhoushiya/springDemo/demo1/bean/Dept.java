package com.zhoushiya.springDemo.demo1.bean;

/**
 * 部门类
 * @author zhoushiya
 * @date 2020/9/11 17:24
 */
public class Dept {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Dept{" +
                "name='" + name + '\'' +
                '}';
    }
}
