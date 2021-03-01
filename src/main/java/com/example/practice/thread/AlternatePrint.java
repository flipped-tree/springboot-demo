package com.example.practice.thread;

/**
 * @author xingce
 * 奇偶数交替打印
 */
public class AlternatePrint {

    private static int num = 1;

    private static final Object lock = new Object();

    public static void main(String[] args) {
        //打印奇数的线程
        new Thread(() -> {
            while (num < 10) {
                try {
                    synchronized (lock) {
                        if (num % 2 == 0) {
                            lock.wait();
                        }
                        System.out.println("奇数：" + num);
                        num++;
                        lock.notifyAll();
                    }

                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }).start();

        //打印偶数的线程
        new Thread(() -> {
            while (num < 10) {
                try {
                    synchronized (lock) {
                        if (num % 2 != 0) {
                            lock.wait();
                        }
                        System.out.println("偶数:" + num);
                        num++;
                        lock.notifyAll();
                    }
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }).start();
    }
}