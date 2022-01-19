package com.example.queue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.CountDownLatch;

/**
 * @author xingce
 * @date 2022/1/17 20:01
 */
public class ProducerArray implements Runnable {
    private final ArrayBlockingQueue<Bread> queue;

    private final CountDownLatch countDownLatch;

    public ProducerArray(ArrayBlockingQueue<Bread> queue, CountDownLatch countDownLatch) {
        this.queue = queue;
        this.countDownLatch = countDownLatch;
    }

    @Override
    public void run() {
        for (int i = 0; i < 100000; i++) {
            produce(i);
        }
        countDownLatch.countDown();
    }

    private void produce(int i) {
        try {
            Bread bread = new Bread();
            bread.setName("" + i);
            queue.put(bread);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
