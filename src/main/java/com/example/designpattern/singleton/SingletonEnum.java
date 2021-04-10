package com.example.designpattern.singleton;

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

    private final OkHttpClient instance;

    SingletonEnum() {
        instance = new okhttp3.OkHttpClient.Builder().
                connectTimeout(30, TimeUnit.SECONDS).
                readTimeout(30, TimeUnit.SECONDS).
                retryOnConnectionFailure(true).build();
    }

    public OkHttpClient getInstance() {
        return instance;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            System.out.println(SingletonEnum.INSTANCE.getInstance());
        }
    }
}
