package com.example.springboot.practice.jdk8;

/**
 * @author xingce
 * @date 2020/12/20 20:47
 */
public class DefaultTest {

    public static void main(String[] args) {
        DefaultInterface defaultInterface = new DefaultInterfaceImpl();
        defaultInterface.test();
    }

}
