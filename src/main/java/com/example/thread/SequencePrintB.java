package com.example.thread;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author xingce
 * @date 2019/11/21 15:20
 */
public class SequencePrintB {

    private static Lock lock = new ReentrantLock();
    /**
     * 用state来判断轮到谁执行
     */
    private static int state = 0;
    /**
     * 表示循环的次数
     */
    private static final int RUN_NUMBER = 1;

    static class ThreadA extends Thread {
        @Override
        public void run() {
            for (int i = 0; i < RUN_NUMBER; ) {
                lock.lock();
                try {
                    if (state % 3 == 0) {
                        System.out.println("第" + (i + 1) + "次:");
                        System.out.println("A");
                        state++;
                        i++;
                    }
                } finally {
                    lock.unlock();
                }
            }
        }
    }

    static class ThreadB extends Thread {
        @Override
        public void run() {
            for (int i = 0; i < RUN_NUMBER; ) {
                lock.lock();
                try {
                    if (state % 3 == 1) {
                        System.out.println("B");
                        state++;
                        i++;
                    }
                } finally {
                    lock.unlock();
                }
            }
        }
    }

    static class ThreadC extends Thread {
        @Override
        public void run() {
            for (int i = 0; i < RUN_NUMBER; ) {
                lock.lock();
                try {
                    if (state % 3 == 2) {
                        System.out.println("C");
                        state++;
                        i++;
                    }
                } finally {
                    lock.unlock();
                }
            }
        }
    }

    public static void main(String[] args) {
        new ThreadC().start();
        new ThreadB().start();
        new ThreadA().start();
    }
}
