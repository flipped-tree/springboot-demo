package com.example.queue;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author xingce
 * @date 2022/1/17 20:04
 */
public class ProducerLinked implements Runnable {

    //容器
    private final LinkedBlockingQueue<Bread> queue;
    private final CountDownLatch countDownLatch;

    public ProducerLinked(LinkedBlockingQueue<Bread> queue, CountDownLatch countDownLatch) {
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

    public void produce(int i) {
        /**
         * put()方法是如果容器满了的话就会把当前线程挂起
         * offer()方法是容器如果满的话就会返回false。
         */
        try {
            Bread bread = new Bread();
            bread.setName("" + i);
            queue.put(bread);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
