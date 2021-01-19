package com.example.springboot.practice.proxy;

/**
 * @author xingce
 * @date 2019/11/26 11:09
 */
public class HelloWorldImpl implements HelloWorldInterface {
    @Override
    public void sayHello() {
        System.out.println("hello world");
    }
}
