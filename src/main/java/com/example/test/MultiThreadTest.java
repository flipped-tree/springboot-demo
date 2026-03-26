package com.example.test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class MultiThreadTest {
    
    public static void main(String[] args) throws InterruptedException {
        ExpiringKeyValueStore<String, Integer> store = 
            new ExpiringKeyValueStore<>(5, TimeUnit.SECONDS);
        
        int threadCount = 10;
        ExecutorService executor = Executors.newFixedThreadPool(threadCount);
        CountDownLatch latch = new CountDownLatch(threadCount);
        
        System.out.println("=== 多线程并发测试 ===");
        
        // 启动多个线程同时进行读写操作
        for (int i = 0; i < threadCount; i++) {
            final int threadId = i;
            executor.submit(() -> {
                try {
                    for (int j = 0; j < 100; j++) {
                        String key = "key-" + threadId + "-" + j;
                        
                        // 写操作
                        store.put(key, threadId * 1000 + j, 10, TimeUnit.SECONDS);
                        
                        // 读操作
                        Integer value = store.get(key);
                        if (value != null && value != threadId * 1000 + j) {
                            System.err.println("数据不一致！");
                        }
                        
                        // 随机删除
                        if (j % 10 == 0) {
                            store.remove(key);
                        }
                    }
                } finally {
                    latch.countDown();
                }
            });
        }
        
        // 等待所有线程完成
        latch.await();
        executor.shutdown();
        
        System.out.println("多线程测试完成");
        System.out.println("最终有效大小: " + store.validSize());
        
        store.shutdown();
    }
}