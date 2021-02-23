package com.example.practice.thread.producer;

import java.util.concurrent.ArrayBlockingQueue;

/**
 * @author xingce
 * @date 2021/02/23 22:51
 */
public class BlockingQueueTest {
    private static final int MAX_CAPACITY = 10;
    private static final ArrayBlockingQueue<Object> GOODS = new ArrayBlockingQueue<Object>(MAX_CAPACITY);

    public static void main(String[] args) {
        (new ProducerThread()).start();

        (new ConsumerThread()).start();
    }

    static class ProducerThread extends Thread {
        @Override
        public void run() {
            while (true) {
                // 每隔 1000 毫秒生产一个商品
                try {
                    GOODS.put(new Object());
                    System.out.println("Produce goods, total: " + GOODS.size());
                } catch (InterruptedException e) {
                }
            }
        }
    }

    static class ConsumerThread extends Thread {
        @Override
        public void run() {
            while (true) {
                // 每隔 500 毫秒消费一个商品
                try {
                    GOODS.take();
                    System.out.println("Consume goods, total: " + GOODS.size());
                } catch (InterruptedException e) {
                }
            }
        }
    }
}
