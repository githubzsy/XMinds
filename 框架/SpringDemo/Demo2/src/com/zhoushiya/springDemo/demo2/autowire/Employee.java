package com.zhoushiya.springDemo.demo2.autowire;

/**
 * @author zhoushiya
 * @date 2020/9/13 11:08
 */
public class Employee {
    private Dept dept;

    public Dept getDept() {
        return dept;
    }

    public void setDept(Dept dept) {
        this.dept = dept;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "dept=" + dept +
                '}';
    }

    public void test(){
        System.out.println(dept);
    }
}
