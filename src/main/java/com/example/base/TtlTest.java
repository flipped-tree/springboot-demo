package com.example.base;


import com.alibaba.ttl.TransmittableThreadLocal;
import com.alibaba.ttl.threadpool.TtlExecutors;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TtlTest {
    //定义另外一个线程池循环执行，模拟业务场景下多Http请求调用的情况
    private static final ExecutorService LOOP_EXECUTOR = TtlExecutors.getTtlExecutorService(Executors.newFixedThreadPool(5));
    //TTL的ThreadLocal
    private static final ThreadLocal<Integer> TTL = new TransmittableThreadLocal<>(); //这里采用TTL的实现

    public static void main(String[] args) {
        if (LOOP_EXECUTOR == null) {
            return;
        }
        for (int j = 0; j < 10; j++) {
            TTL.set(j);
            LOOP_EXECUTOR.execute(() -> System.out.println(TTL.get()));
        }
        LOOP_EXECUTOR.shutdown();
    }
}
