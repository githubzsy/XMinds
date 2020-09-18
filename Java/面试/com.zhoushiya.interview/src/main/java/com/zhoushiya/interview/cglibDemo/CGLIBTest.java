package com.zhoushiya.interview.cglibDemo;

import net.sf.cglib.proxy.*;
import org.junit.Test;

import java.lang.reflect.Method;

/**
 * @author zhoushiya
 * @date 2020/9/10 19:55
 */
public class CGLIBTest {


    @Test
    public void testFixedValue() {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(SampleClass.class);
        Callback callback = new MethodInterceptor() {
            /**
             * 对被代理类方法进行拦截
             * @param obj 当前增强对象
             * @param method 触发的被代理类的方法
             * @param args 参数
             * @param proxy 对应的代理类方法
             * @return
             * @throws Throwable
             */
            @Override
            public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
                System.out.println("obj的类型:" + obj.getClass());

                // 错误代码示范：因为这里拦截器拦截了被代理类的方法，如果直接使用method.invoke会进入死循环，应该改为proxy.invokeSuper()
                // return method.invoke(obj,args);
                // 错误代码：与上面同理，proxy.invoke调用的是被代理对象方法，同样会进入死循环，导致栈溢出
                // proxy.invoke(obj,args);
                return proxy.invokeSuper(obj, args);
            }
        };
        enhancer.setCallback(callback);
        SampleClass proxy = (SampleClass) enhancer.create();
        //拦截test，输出Hello cglib
        System.out.println(proxy.test(null));
        System.out.println(proxy.toString());
        System.out.println(proxy.getClass());
        System.out.println(proxy.hashCode());
    }
}
