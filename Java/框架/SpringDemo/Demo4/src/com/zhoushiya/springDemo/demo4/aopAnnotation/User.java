package com.zhoushiya.springDemo.demo4.aopAnnotation;

import org.springframework.stereotype.Component;

/**
 * @author zhoushiya
 * @date 2020/9/14 12:20
 */
@Component
public class User {
    public void add(){
        //int i = 1 / 0;
        System.out.println("user add...");
    }
}
