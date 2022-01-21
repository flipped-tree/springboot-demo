package com.example.singleton;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author xingce
 * @date 2022/1/21 10:28
 */
public class Singleton<T> {
    private T value;

    public static void main(String[] args) {

        AtomicInteger num = new AtomicInteger();

        ExecutorService service = new ThreadPoolExecutor(5, 5, 10, TimeUnit.SECONDS, new ArrayBlockingQueue<>(10),
                r -> new Thread(r, "thread-pool-" + num.getAndIncrement() + ": "));
        for (int i = 0; i < 10; i++) {
            service.execute(() -> {
                System.out.println(Thread.currentThread().getName() + " " + CasSingleton.getInstance());
            });
        }

        service.shutdown();
    }
}
