package com.zhoushiya.myJdkProxy;

import java.lang.reflect.Method;

/**
 * @author zhoushiya
 * @date 2020/9/24 19:00
 */
public interface IMyInvocationHandler {
    Object invoke(Object proxy, Method method, Object[] args) throws Throwable;
}
