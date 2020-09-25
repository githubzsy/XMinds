package com.zhoushiya.myJdkProxy;

import java.io.UnsupportedEncodingException;

/**
 * @author zhoushiya
 * @date 2020/9/25 16:38
 */
public class Main {
    public static void main(String[] args) throws UnsupportedEncodingException {
        /**
         *  问：为何Handler的构造函数必须是实现了接口的对象，随便一个Object不行吗？
         *  答：从ProxyHandler.invoke()方法中可以看出：method.invoke(object,args)。这里的method与object必须是相对应的。也就是说object必须实现了这个method。实际上method是由接口产生的，所以必须要求一个实现接口的对象去调用。如果不是这个接口的对象，会报错：java.lang.IllegalArgumentException: object is not an instance of declaring class
         **/
        IAnimal animal = (IAnimal) MyProxy.newProxyInstance(new MyClassLoader(), new Class[]{IAnimal.class}, new ProxyHandler(new Dog()));
        animal.eat("肉");


        // $Proxy0 $Proxy0 = new $Proxy0(new ProxyHandler(new Object()));
        // $Proxy0.eat("肉");
    }
}
