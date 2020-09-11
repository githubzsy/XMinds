package com.zhoushiya.springDemo.demo1.bean;

/**
 * 员工类
 * @author zhoushiya
 * @date 2020/9/11 17:25
 */
public class Employee {
    private String name;
    private boolean gender;
    /**
     * 所属部门
     */
    private Dept dept;

    public Dept getDept() {
        return dept;
    }

    public void setDept(Dept dept) {
        this.dept = dept;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", gender=" + gender +
                ", dept=" + dept +
                '}';
    }
}
