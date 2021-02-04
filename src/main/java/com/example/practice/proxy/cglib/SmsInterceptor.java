package com.example.practice.proxy.cglib;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author xingce
 * @date 2020/12/16 17:42
 */
public class SmsInterceptor implements MethodInterceptor {

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("send before");
        Object object = methodProxy.invokeSuper(o, objects);
        System.out.println("send after");
        return object;
    }
}
