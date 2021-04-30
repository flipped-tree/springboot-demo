package com.example.thread;

/**
 * @author xingce
 * 奇偶数交替打印
 */
public class AlternatePrint {

    private static int num = 1;

    private static final Object LOCK = new Object();

    public static void main(String[] args) {
        //打印奇数的线程
        new Thread(() -> {
            synchronized (LOCK) {
                try {
                    while (num < 10) {
                        if (num % 2 == 0) {
                            LOCK.wait();
                        }
                        System.out.println("奇数：" + num);
                        num++;
                        LOCK.notifyAll();
                    }

                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }).start();

        //打印偶数的线程
        new Thread(() -> {
            synchronized (LOCK) {
                try {
                    while (num < 10) {
                        if (num % 2 != 0) {
                            LOCK.wait();
                        }
                        System.out.println("偶数:" + num);
                        num++;
                        LOCK.notifyAll();
                    }
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }).start();
    }
}