package com.zhoushiya.springDemo.demo4.aopAnnotation;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @author zhoushiya
 * @date 2020/9/14 15:55
 */
@Component
@Aspect
@Order(1)
public class PersonProxy {
    @Before(value = "execution(* User.add(..))")
    public void before(){
        System.out.println("PersonProxy Before...");
    }
}
