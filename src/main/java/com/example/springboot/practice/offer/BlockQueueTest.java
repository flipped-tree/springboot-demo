package com.example.springboot.practice.offer;

import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author xingce
 * @date 2020/12/17 20:06
 */
public class BlockQueueTest {

    public static void main(String[] args) throws InterruptedException {
        LinkedBlockingQueue<Integer> queue = new LinkedBlockingQueue<>(4);
        queue.put(1);
        queue.put(2);
        queue.put(3);
        queue.put(4);

        System.out.println(queue.size());
    }

}
