package com.example.practice.thread;

/**
 * 线程间通讯
 *
 * @author xingce
 * @date 2019/8/2 11:55
 */
public class ObjectWaitTest {

    private static volatile boolean lock;

    public static void main(String[] args) throws InterruptedException {
        final Object object = new Object();
        System.out.println(lock);

        Thread thread1 = new Thread(() -> {
            System.out.println("等待被通知！");
            try {
                synchronized (object) {
                    while (!lock) {
                        object.wait();
                    }
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("已被通知");
        });
        Thread thread2 = new Thread(() -> {
            System.out.println("马上开始通知！");
            synchronized (object) {
                object.notify();
                lock = true;
            }
            System.out.println("已通知");
        });
        thread1.start();
        thread2.start();
        Thread.sleep(10);
    }
}
