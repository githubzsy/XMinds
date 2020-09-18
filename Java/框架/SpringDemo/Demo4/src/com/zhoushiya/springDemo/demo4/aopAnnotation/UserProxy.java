package com.zhoushiya.springDemo.demo4.aopAnnotation;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * 增强类
 * @author zhoushiya
 * @date 2020/9/14 12:21
 */
// 增强的类
@Component
// 生成代理对象
@Aspect
// 设置增强类的优先级，越小优先级越高
@Order(2)
public class UserProxy {

    @Pointcut("execution(* User.add(..))")
    public void point(){

    }

    /**
     * 前置通知
     */
    // 省略了全包名
    @Before(value = "execution(* User.add(..))")
    public void before(){
        System.out.println("before...");
    }

    /**
     * 后置通知，始终会执行
     */
    @After("point()")
    public void after(){
        System.out.println("after...");
    }

    /**
     * 后置返回通知，出现异常后不会执行
     */
    @AfterReturning(value = "execution(* User.add(..))")
    public void afterReturning(){
        System.out.println("afterReturning...");
    }

    /**
     * 后置异常通知，出现异常后会执行
     */
    @AfterThrowing(value = "execution(* User.add(..))")
    public void afterThrowing(){
        System.out.println("afterThrowing...");
    }


    /**
     * 环绕通知，被增强方法的之前之后都执行
     */
    @Around(value = "execution(* User.add(..))")
    public void around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        System.out.println("before around...");
        // 如果不执行proceedingJoinPoint.proceed()会导致其它动作(原有方法以及增强方法)都不会之执行
        proceedingJoinPoint.proceed();
        System.out.println("after around...");
    }
}
