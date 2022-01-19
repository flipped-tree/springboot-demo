package com.example.queue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author xingce
 * @date 2022/1/17 20:06
 */
public class PerformanceDemo {

    public static void main(String[] args) throws InterruptedException {
        int capacity = 9000000;
        // put in ArrayBlockingQueue size:=1000000,use time:=234
//        testArray(capacity);
        // put in LinkedBlockingQueue size:=1000000,use time:=503
        testLinked(capacity);
    }

    private static void testArray(int capacity) throws InterruptedException {
        ArrayBlockingQueue<Bread> queue = new ArrayBlockingQueue<>(capacity);
        CountDownLatch countDownLatch = new CountDownLatch(10);
        ExecutorService service = Executors.newFixedThreadPool(10);
        long start = System.currentTimeMillis();
        for (int i = 0; i < 10; i++) {
            service.submit(new ProducerArray(queue, countDownLatch));
        }
        countDownLatch.await();
        long end = System.currentTimeMillis();
        service.shutdown();
        System.out.println("put in ArrayBlockingQueue size:=" + queue.size() + ",use time:=" + (end - start));
    }

    private static void testLinked(int capacity) throws InterruptedException {
        LinkedBlockingQueue<Bread> queue = new LinkedBlockingQueue<>(capacity);
        CountDownLatch countDownLatch = new CountDownLatch(10);
        ExecutorService service = Executors.newFixedThreadPool(10);
        long start = System.currentTimeMillis();
        for (int i = 0; i < 10; i++) {
            service.submit(new ProducerLinked(queue, countDownLatch));
        }
        countDownLatch.await();
        long end = System.currentTimeMillis();
        service.shutdown();
        System.out.println("put in LinkedBlockingQueue size:=" + queue.size() + ",use time:=" + (end - start));
    }

}
