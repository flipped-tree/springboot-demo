package com.example.designpattern.proxy.jdk;

import java.lang.reflect.Proxy;

/**
 * @author xingce
 * @date 2020/12/16 21:14
 */
public class JdkProxyFactory {

    public static Object getProxy(Object target) {
        return Proxy.newProxyInstance(target.getClass().getClassLoader(),
                target.getClass().getInterfaces(),
                (proxy, method, args) -> {
                    System.out.println("短信发送前");
                    Object object = method.invoke(target, args);
                    System.out.println("短信发送后");
                    return object;
                });
    }

}
