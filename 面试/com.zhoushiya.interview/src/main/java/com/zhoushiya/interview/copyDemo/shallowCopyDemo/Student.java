package com.zhoushiya.interview.copyDemo.shallowCopyDemo;

public class Student implements Cloneable {

    //引用类型
    private Subject subject;
    //基础数据类型
    private String name;
    private int age;

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

//    /**
//     *  重写clone()方法
//     * @return
//     */
//    @Override
//    public Object clone() {
//        //浅拷贝
//        try {
//            // 直接调用父类的clone()方法
//            return super.clone();
//        } catch (CloneNotSupportedException e) {
//            return null;
//        }
//    }


    /**
     * 深拷贝
     * @return
     * @throws CloneNotSupportedException
     */
    @Override
    protected Object clone() throws CloneNotSupportedException {
        Student student = (Student) super.clone();
        // 每个引用属性的成员都要拷贝
        student.subject = (Subject) subject.clone();
        return student;
    }

    @Override
    public String toString() {
        return "[Student: " + this.hashCode() + ",subject:" + subject + ",name:" + name + ",age:" + age + "]";
    }
}