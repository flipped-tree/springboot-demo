package com.example.springboot.util;

import okhttp3.OkHttpClient;

import java.util.concurrent.TimeUnit;

/**
 * @author xingce
 * @date 2020/12/17 11:52
 */
public class OkHttpUtil {

    private OkHttpUtil() {

    }

    private static class OkHttpClientHolder {
        private static final OkHttpClient INSTANCE = new okhttp3.OkHttpClient.Builder().
                connectTimeout(30, TimeUnit.SECONDS).
                readTimeout(30, TimeUnit.SECONDS).
                retryOnConnectionFailure(true).build();
    }

    public static OkHttpClient getInstance() {
        return OkHttpClientHolder.INSTANCE;
    }

}
