package org.example.mymapper.factorybean;

import org.springframework.beans.factory.FactoryBean;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;

/**
 * 生成Mapper代理类的Bean
 * @author zhoushiya
 * @date 2020/10/11 19:33
 */

public class MyFactoryBean implements FactoryBean {

    private final Class mapperClass;

    public MyFactoryBean(Class mapperClass) {
        this.mapperClass = mapperClass;
    }

    public Object getObject() throws Exception {
        Object o = Proxy.newProxyInstance(this.getObjectType().getClassLoader(), new Class[]{mapperClass}, new InvocationHandler() {
            /**
             * 代理原方法
             * @param proxy 当前代理对象
             * @param method 要执行的原方法
             * @param args 参数
             * @return
             * @throws Throwable
             */
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                // System.out.println("proxy:"+proxy);
                System.out.println("args:"+args);
                System.out.println("method:"+method.getName());

                return "返回值";
            }
        });
        return o;
    }

    public Class<?> getObjectType() {
        return mapperClass;
    }
}
