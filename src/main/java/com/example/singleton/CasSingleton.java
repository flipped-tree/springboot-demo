package com.example.singleton;

import java.util.concurrent.atomic.AtomicReference;

/**
 * @author xingce
 * @date 2021/03/26 23:05
 */
public class CasSingleton {
    private static final AtomicReference<Singleton<Integer>> INSTANCE = new AtomicReference<>();

    private CasSingleton() {
    }

    public static Singleton<Integer> getInstance() {
        while (true) {
            Singleton<Integer> singleton = INSTANCE.get();
            if (singleton != null) {
                return singleton;
            }
            singleton = new Singleton<>();
            if (INSTANCE.compareAndSet(null, singleton)) {
                return singleton;
            }
        }
    }
}
