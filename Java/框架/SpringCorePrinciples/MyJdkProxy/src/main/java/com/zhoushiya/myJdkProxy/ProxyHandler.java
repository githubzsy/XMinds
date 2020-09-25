package com.zhoushiya.myJdkProxy;

import java.lang.reflect.Method;

/**
 * 代理handler类
 *
 * @author zhoushiya
 * @date 2020/9/25 16:26
 */
public class ProxyHandler implements IMyInvocationHandler {

    private Object object;

    /**
     * 获取一个代理类Handler
     * @param object 实现接口的对象
     */
    public ProxyHandler(Object object) {
        this.object = object;
    }

    /**
     * 调用方法（当代理类执行方法时实际执行的方法）
     * @param proxy 实现接口的代理类
     * @param method 调用的方法（实际通过接口得到的）
     * @param args 参数
     * @return 方法返回值
     * @throws Throwable
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        before();
        method.invoke(this.object, args);
        after();
        return null;
    }

    private void before() {
        System.out.println("ProxyHandler before");
    }

    private void after() {
        System.out.println("ProxyHandler after");
    }
}
