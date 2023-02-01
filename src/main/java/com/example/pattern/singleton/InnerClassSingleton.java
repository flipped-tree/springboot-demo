package com.example.pattern.singleton;

/**
 * @author xingce
 * @date 2021/03/26 22:50
 */
public class InnerClassSingleton {

    private static class SingletonHolder {
        private static final Singleton INSTANCE = new Singleton();
    }

    private InnerClassSingleton() {
    }

    public static Singleton getInstance() {
        return SingletonHolder.INSTANCE;
    }

}
