package com.example.designpattern.singleton;

/**
 * @author xingce
 * @date 2021/03/26 22:50
 */
public class InnerClassSingleton {

    private static class SingletonHolder {
        private static final InnerClassSingleton INSTANCE = new InnerClassSingleton();
    }

    private InnerClassSingleton() {
    }

    public static InnerClassSingleton getInstance() {
        return SingletonHolder.INSTANCE;
    }
}
