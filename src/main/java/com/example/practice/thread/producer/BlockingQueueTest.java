package com.example.practice.thread.producer;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author xingce
 * @date 2018/11/22 4:05 PM
 */
public class BlockingQueueTest {

    public static void main(String[] args) {
        BlockingQueue<Integer> sharedQueue = new LinkedBlockingQueue<>(10);
        Thread producer = new Thread(new Producer(sharedQueue));
        Thread consumer = new Thread(new Consumer(sharedQueue));
        producer.start();
        consumer.start();
    }

    static class Producer implements Runnable {

        BlockingQueue<Integer> sharedQueue;

        Producer(BlockingQueue<Integer> queue) {
            this.sharedQueue = queue;
        }

        @Override
        public void run() {
            for (int i = 1; i <= 5; i++) {
                try {
                    System.out.println(Thread.currentThread().getName() + "|生产者|" + i);
                    sharedQueue.put(i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    static class Consumer implements Runnable {

        BlockingQueue<Integer> sharedQueue;

        Consumer(BlockingQueue<Integer> queue) {
            this.sharedQueue = queue;
        }

        @Override
        public void run() {
            for (int i = 1; i <= 10; i++) {
                try {
                    System.out.println(Thread.currentThread().getName() + "|消费者|" + sharedQueue.take());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
