package com.zhoushiya.interview.copyDemo.shallowCopyDemo;

/**
 * @author zhoushiya
 * @date 2020/9/15 16:18
 */
public class objectCopy {
    public static void main(String[] args) {
        Subject subject = new Subject("yuwen");
        Student studentA = new Student();
        studentA.setSubject(subject);
        studentA.setName("Lynn");
        studentA.setAge(20);
        Student studentB = studentA;
        studentB.setName("Lily");
        studentB.setAge(18);
        Subject subjectB = studentB.getSubject();
        subjectB.setName("lishi");
        System.out.println("studentA:" + studentA.toString());
        System.out.println("studentB:" + studentB.toString());
        // studentA:[Student: 1265094477,subject:[Subject: 2125039532,name:lishi],name:Lily,age:18]
        // studentB:[Student: 1265094477,subject:[Subject: 2125039532,name:lishi],name:Lily,age:18]
        // 可见，对象拷贝后没有生成新的对象，二者的对象地址是一样的；而浅拷贝的对象地址是不一样的
    }
}
