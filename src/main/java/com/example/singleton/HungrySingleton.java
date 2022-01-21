package com.example.singleton;

/**
 * @author xingce
 * @date 2021/03/26 22:42
 */
public class HungrySingleton {
    private static final Singleton<Integer> INSTANCE = new Singleton<>();

    private HungrySingleton(){}

    public static Singleton<Integer> getInstance() {
        return INSTANCE;
    }
}
