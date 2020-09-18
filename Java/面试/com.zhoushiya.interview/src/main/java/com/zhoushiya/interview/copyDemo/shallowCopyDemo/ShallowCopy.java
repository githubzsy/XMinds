package com.zhoushiya.interview.copyDemo.shallowCopyDemo;

public class ShallowCopy {
    public static void main(String[] args) throws CloneNotSupportedException {
        Subject subject = new Subject("yuwen");
        Student studentA = new Student();
        studentA.setSubject(subject);
        studentA.setName("Lynn");
        studentA.setAge(20);
        Student studentB = (Student) studentA.clone();
        studentB.setName("Lily");
        studentB.setAge(18);
        Subject subjectB = studentB.getSubject();
        subjectB.setName("lishi");
        System.out.println("studentA:" + studentA.toString());
        System.out.println("studentB:" + studentB.toString());

        // 浅拷贝时的结果
        // studentA:[Student: 1265094477,subject:[Subject: 2125039532,name:lishi],name:Lynn,age:20]
        // studentB:[Student: 312714112,subject:[Subject: 2125039532,name:lishi],name:Lily,age:18]
        //由输出的结果可见，通过 studentA.clone() 拷贝对象后得到的 studentB，和 studentA 是两个不同的对象。studentA 和 studentB 的基础数据类型的修改互不影响，而引用类型 subject 修改后是会有影响的

        // 深拷贝时的结果
        // studentA:[Student: 1265094477,subject:[Subject: 2125039532,name:yuwen],name:Lynn,age:20]
        // studentB:[Student: 312714112,subject:[Subject: 692404036,name:lishi],name:Lily,age:18]
        // 深拷贝是分解到最基本属性的值拷贝，两者之间互不影响
    }
}