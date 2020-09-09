package com.zhoushiya.interview.proxyDemo;

/**
 * 静态代理类
 */
class HelloProxy implements HelloInterface {
    private HelloInterface helloInterface = new Hello();

    @Override
    public void sayHello() {
        System.out.println("Before invoke sayHello");
        helloInterface.sayHello();
        System.out.println("After invoke sayHello");
    }
}


/**
 * 静态代理示例
 * @author zhoushiya
 * @date 2020/9/2 18:29
 */
public class StaticProxyDemo {
    public static void main(String[] args) {
        HelloProxy helloProxy = new HelloProxy();
        helloProxy.sayHello();
    }
}
