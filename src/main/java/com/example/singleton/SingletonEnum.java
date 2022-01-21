package com.example.singleton;

import okhttp3.OkHttpClient;

import java.util.concurrent.TimeUnit;

/**
 * @author xingce
 * @date 2021/03/26 22:52
 */
public enum SingletonEnum {
    /**
     * 枚举实现单例模式
     */
    INSTANCE;

    private final Singleton<Integer> instance;

    SingletonEnum() {
        instance = new Singleton<>();
    }

    public Singleton<Integer> getInstance() {
        return instance;
    }
}
