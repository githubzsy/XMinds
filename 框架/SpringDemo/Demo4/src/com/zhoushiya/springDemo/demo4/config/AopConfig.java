package com.zhoushiya.springDemo.demo4.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @author zhoushiya
 * @date 2020/9/14 17:16
 */
@Configuration
@ComponentScan(basePackages = {"com.zhoushiya.springDemo.demo4"})
// 开启AspectJ自动代理(去实现aop)
// proxyTargetClass:是否所有代理都用CGLIB实现，(false的话，接口代理会用jdk原生方法实现）
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class AopConfig {

}
