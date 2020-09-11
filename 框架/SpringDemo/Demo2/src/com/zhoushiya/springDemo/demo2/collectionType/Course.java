package com.zhoushiya.springDemo.demo2.collectionType;

/**
 * @author zhoushiya
 * @date 2020/9/11 17:55
 */
public class Course {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Course{" +
                "name='" + name + '\'' +
                '}';
    }
}
