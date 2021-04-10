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

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            System.out.println(LazySingleton.getInstance());
        }
    }
}
