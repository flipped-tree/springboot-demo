package com.example.springboot.practice.jdk8;

/**
 * @author xingce
 * @date 2020/12/20 20:35
 */
public class DefaultInterfaceImpl implements DefaultInterface {

    @Override
    public void test() {
        System.out.println("test impl");
    }
}
