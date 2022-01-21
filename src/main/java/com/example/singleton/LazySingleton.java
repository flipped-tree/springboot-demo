package com.example.singleton;

/**
 * @author xingce
 * @date 2021/03/26 22:44
 */
public class LazySingleton {
    private static volatile Singleton<Integer> INSTANCE;

    private LazySingleton() {
    }

    public static Singleton<Integer> getInstance() {
        if (INSTANCE == null) {
            synchronized (LazySingleton.class) {
                if (INSTANCE == null) {
                    INSTANCE = new Singleton<>();
                }
            }
        }
        return INSTANCE;
    }
}
