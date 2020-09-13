package com.zhoushiya.springDemo.demo2.bean;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

/**
 * @author zhoushiya
 * @date 2020/9/13 10:58
 */
public class MyBeanPostProcessor implements BeanPostProcessor {

    /**
     * 初始化之前的后置处理
     * @param bean
     * @param beanName
     * @return
     * @throws BeansException
     */
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("初始化之前的后置处理");
        return bean;
    }

    /**
     * 初始化之后的后置处理
     * @param bean
     * @param beanName
     * @return
     * @throws BeansException
     */
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("初始化之后的后置处理");
        return bean;
    }
}
