package com.example.singleton;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

/**
 * @author xingce
 * @date 2022/1/21 10:28
 */
public class Singleton<T> {

    public static void main(String[] args) {

        AtomicInteger num = new AtomicInteger();

        ExecutorService service = new ThreadPoolExecutor(5, 5, 10, TimeUnit.SECONDS, new ArrayBlockingQueue<>(10),
                r -> new Thread(r, "thread-pool-" + num.getAndIncrement() + ": "));
        IntStream.range(0, 10).forEach(count ->
                service.execute(() -> System.out.println(Thread.currentThread().getName() + " " +
                        CasSingleton.getInstance())));

        service.shutdown();
    }
}
