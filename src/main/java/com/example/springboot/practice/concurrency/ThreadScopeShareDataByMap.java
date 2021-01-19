package com.example.springboot.practice.concurrency;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * @author xingce
 * @date 2019/12/27 19:11
 */
public class ThreadScopeShareDataByMap {
    /**
     * 公共的数据
     */
    private static int data = 0;

    private static Map<Thread, Integer> threadData = Collections.synchronizedMap(new HashMap<>());

    public static void main(String[] args) {
        for (int i = 0; i < 2; i++) {
            new Thread(() -> {
                int temp = new Random().nextInt();
                System.out.println(Thread.currentThread().getName() + " has put data:" + temp);
                threadData.put(Thread.currentThread(), temp);
                data = temp;
                new TestA().getData();
                new TestB().getData();
            }).start();
        }
    }

    static class TestA {
        void getData() {
            //取出各线程维护的那个副本
            System.out.println("A get data from " + Thread.currentThread().getName() + ": "
                    + threadData.get(Thread.currentThread()));
        }
    }

    static class TestB {
        void getData() {
            System.out.println("B get data from " + Thread.currentThread().getName() + ": "
                    + threadData.get(Thread.currentThread()));
        }
    }
}
