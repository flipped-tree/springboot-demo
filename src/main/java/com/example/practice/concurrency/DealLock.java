package com.example.practice.concurrency;

/**
 * @author xingce
 * @date 2019/12/20 11:09
 */
public class DealLock {

    public static void main(String[] args) {
        new Thread(new MyRunnable(true)).start();
        new Thread(new MyRunnable(false)).start();
    }
}

/**
 * 把两把锁放到一个类中定义，是为了两个线程使用的都是这两把锁
 */
class MyLock {
    public static final Object lock_a = new Object();
    public static final Object lock_b = new Object();
}

class MyRunnable implements Runnable {

    private boolean flag;

    MyRunnable(boolean flag) {
        this.flag = flag;
    }

    @Override
    public void run() {
        if (flag) {
            while (true) {
                synchronized (MyLock.lock_a) {
                    System.out.println("---threadA---lock_a---");
                    synchronized (MyLock.lock_b) {
                        System.out.println("---threadA---lock_b---");
                    }
                }
            }
        } else {
            while (true) {
                synchronized (MyLock.lock_b) {
                    System.out.println("---threadB---lock_b---");
                    synchronized (MyLock.lock_a) {
                        System.out.println("---threadB---lock_a---");
                    }
                }
            }
        }
    }
}