package com.example.springboot.practice.proxy;

/**
 * @author xingce
 * @date 2019/11/26 11:10
 */
public class Main {
    public static void main(String[] args) {
        LogProxy logProxy = new LogProxy();
        HelloWorldInterface helloWorldInterface = (HelloWorldInterface) logProxy.getProxyObject(new HelloWorldImpl());
        helloWorldInterface.sayHello();
    }
}
