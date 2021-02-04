package com.example.practice.proxy.cglib;

import net.sf.cglib.proxy.Enhancer;

/**
 * @author xingce
 * @date 2020/12/16 17:44
 */
public class CglibFactory {

    public static Object getObject(Class<?> clazz){
        Enhancer enhancer = new Enhancer();
        enhancer.setClassLoader(clazz.getClassLoader());
        enhancer.setSuperclass(clazz);
        enhancer.setCallback(new SmsInterceptor());
        return enhancer.create();
    }

}
