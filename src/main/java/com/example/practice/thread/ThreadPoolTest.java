package com.example.practice.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author xingce
 * @date 2021/3/6 13:20
 * 判断线程池是否结束
 */
public class ThreadPoolTest {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(3);
        long start = System.currentTimeMillis();
        for (int i = 0; i < 5; i++) {
            executor.submit(() -> {
                try {
                    TimeUnit.MILLISECONDS.sleep(1000L);
                    System.out.println(Thread.currentThread().getName() + " do something");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }

        executor.shutdown();
        while (true) {
            if (executor.isTerminated()) {
                System.out.println("end time:" + (System.currentTimeMillis() - start));
                break;
            }
        }
    }
}
