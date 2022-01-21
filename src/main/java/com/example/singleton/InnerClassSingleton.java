package com.example.singleton;

/**
 * @author xingce
 * @date 2021/03/26 22:50
 */
public class InnerClassSingleton {

    private static class SingletonHolder {
        private static final Singleton<Integer> INSTANCE = new Singleton<>();
    }

    private InnerClassSingleton() {
    }

    public static Singleton<Integer> getInstance() {
        return SingletonHolder.INSTANCE;
    }

}
