package com.zhoushiya.interview.proxyDemo;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

class ProxyHandler implements InvocationHandler {
    private Object object;

    /**
     * 被代理类对象
     *
     * @param object
     */
    public ProxyHandler(Object object) {
        this.object = object;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("开始调用方法:" + method.getName());
        method.invoke(object, args);
        System.out.println("结束调用方法:" + method.getName());
        return null;
    }
}


/**
 * 动态代理示例
 *
 * @author zhoushiya
 * @date 2020/9/2 18:28
 */
public class DynamicProxyDemo {
    public static void main(String[] args) {
        /**
         * 将动态代理生成的代理类存放到本地文件中
         */
        System.getProperties().setProperty("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");

        ProxyHandler helloHandler = new ProxyHandler(new Hello());
        HelloInterface proxyInstance = (HelloInterface) Proxy.newProxyInstance(Hello.class.getClassLoader(), Hello.class.getInterfaces(), helloHandler);
        proxyInstance.sayHello();

        ProxyHandler byeHandler = new ProxyHandler(new Bye());
        ByeInterface byeInterface = (ByeInterface) Proxy.newProxyInstance(Bye.class.getClassLoader(), Bye.class.getInterfaces(), byeHandler);
        byeInterface.sayBye();
    }
}
