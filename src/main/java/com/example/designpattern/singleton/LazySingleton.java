package com.example.designpattern.singleton;

/**
 * @author xingce
 * @date 2021/03/26 22:44
 */
public class LazySingleton {
    private static volatile LazySingleton INSTANCE;

    private LazySingleton() {
    }

    public static LazySingleton getInstance() {
        if (INSTANCE == null) {
            synchronized (LazySingleton.class) {
                if (INSTANCE == null) {
                    INSTANCE = new LazySingleton();
                }
            }
        }
        return INSTANCE;
    }
}
