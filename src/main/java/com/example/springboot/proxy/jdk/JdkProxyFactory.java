package com.example.springboot.proxy.jdk;

import java.lang.reflect.Proxy;

/**
 * @author xingce
 * @date 2020/12/16 21:14
 */
public class JdkProxyFactory {

    public static Object getProxy(Object target){
        return Proxy.newProxyInstance(target.getClass().getClassLoader(),
                target.getClass().getInterfaces(),
                new MessageHandler(target));
    }

}
