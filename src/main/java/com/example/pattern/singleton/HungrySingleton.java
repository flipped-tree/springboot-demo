package com.example.pattern.singleton;

/**
 * @author xingce
 * @date 2021/03/26 22:42
 */
public class HungrySingleton {
    private static final Singleton INSTANCE = new Singleton();

    private HungrySingleton() {
    }

    public static Singleton getInstance() {
        return INSTANCE;
    }
}
