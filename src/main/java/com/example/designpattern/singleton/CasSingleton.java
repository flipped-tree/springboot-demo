package com.example.designpattern.singleton;

import java.util.concurrent.atomic.AtomicReference;

/**
 * @author xingce
 * @date 2021/03/26 23:05
 */
public class CasSingleton {
    private static final AtomicReference<CasSingleton> INSTANCE = new AtomicReference<>();

    private CasSingleton() {
    }

    public static CasSingleton getInstance() {
        while (true) {
            CasSingleton singleton = INSTANCE.get();
            if (singleton != null) {
                return singleton;
            }
            singleton = new CasSingleton();
            if (INSTANCE.compareAndSet(null, singleton)) {
                return singleton;
            }
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            System.out.println(CasSingleton.getInstance());
        }
    }
}
