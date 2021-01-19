package com.example.springboot.test;

import java.util.concurrent.CountDownLatch;

/**
 * 测试类
 *
 * @author xingce
 * @date 2021/01/07 11:30
 */
public class Demo {

    public static void main(String[] args) throws InterruptedException {
        String filePath = "C:\\Users\\xingce\\Desktop\\test";
        Thread producer = new Thread(new Producer(filePath, 10));
        producer.start();

        producer.join();
        CountDownLatch latch = new CountDownLatch(1);
        Consumer consumer = new Consumer();
        consumer.setLatch(latch);
        new Thread(consumer).start();

        latch.await();
        DataHolder.getTreeMap().forEach((key, val) -> System.out.println(val));
    }

}
