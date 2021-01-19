package com.example.springboot.practice.concurrency;

/**
 * @author xingce
 * @date 2020/1/5 16:35
 */
public class MultiThreadShareData {

    public static void main(String[] args) {
        ShareData task = new ShareData();
        for (int i = 0; i < 2; i++) {
            new Thread(task::increment).start();
        }

        for (int i = 0; i < 2; i++) {
            new Thread(task::decrement).start();
        }
    }

}

class ShareData {
    private int data = 0;

    public synchronized void increment() {
        System.out.println(Thread.currentThread().getName() + ": before : " + data);
        data++;
        System.out.println(Thread.currentThread().getName() + ": after : " + data);
    }

    public synchronized void decrement() {
        System.out.println(Thread.currentThread().getName() + ": before : " + data);
        data--;
        System.out.println(Thread.currentThread().getName() + ": after : " + data);
    }
}