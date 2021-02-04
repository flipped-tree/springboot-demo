package com.example.practice.jdk8;

/**
 * @author xingce
 * @date 2020/12/20 20:34
 */
public interface DefaultInterface {

    default void test() {
        System.out.println("test default method");
    }

}
