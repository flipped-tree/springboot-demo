package com.example.springboot.practice.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author xingce
 * @date 2019/11/26 11:07
 */
public class LogProxy implements InvocationHandler {

    private Object object;

    Object getProxyObject(Object o) {
        object = o;
        return Proxy.newProxyInstance(this.getClass().getClassLoader(), o.getClass().getInterfaces(), this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("before invoke ...");
        Object result = method.invoke(object, args);
        System.out.println("after invoke ...");
        return result;
    }
}
