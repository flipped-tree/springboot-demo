package com.example.designpattern.proxy.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author xingce
 * @date 2020/12/16 21:09
 */
public class MessageHandler implements InvocationHandler {

    private final Object target;

    public MessageHandler(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("短信发送前");
        Object object = method.invoke(target, args);
        System.out.println("短信发送后");
        return object;
    }
}
