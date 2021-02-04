package com.example.practice.thread;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;

public class LeetCodeThread1 {

    public static void main(String[] args) {
        Foo_3 foo = new Foo_3();
        new Thread(() -> {
            try {
                foo.first(() -> {
                    System.out.println("first");
                });
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(() -> {
            try {
                foo.second(() -> {
                    System.out.println("second");
                });
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(() -> {
            try {
                foo.third(() -> {
                    System.out.println("third");
                });
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }

}

/**
 * synchronized + notify
 */
class Foo_1 {
    private int flag = 0;
    private final Object lock = new Object();

    public Foo_1() {
    }

    public void first(Runnable printFirst) throws InterruptedException {
        synchronized (lock) {
            //如果flag不为0则让first线程等待，while循环控制first线程如果不满住条件就一直在while代码块中，防止出现中途跳入，执行下面的代码，其余线程while循环同理
            while (flag != 0) {
                lock.wait();
            }
            // printFirst.run() outputs "first". Do not change or remove this line.
            printFirst.run();
            //定义成员变量为 1
            flag = 1;
            //唤醒其余所有的线程
            lock.notifyAll();
        }
    }

    public void second(Runnable printSecond) throws InterruptedException {
        synchronized (lock) {
            //如果成员变量不为1则让二号等待
            while (flag != 1) {
                lock.wait();
            }
            // printSecond.run() outputs "second". Do not change or remove this line.
            printSecond.run();
            //如果成员变量为 1 ，则代表first线程刚执行完，所以执行second，并且改变成员变量为 2
            flag = 2;
            //唤醒其余所有的线程
            lock.notifyAll();
        }
    }

    public void third(Runnable printThird) throws InterruptedException {
        synchronized (lock) {
            //如果flag不等于2 则一直处于等待的状态
            while (flag != 2) {
                lock.wait();
            }
            // printThird.run() outputs "third". Do not change or remove this line.
            //如果成员变量为 2 ，则代表second线程刚执行完，所以执行third，并且改变成员变量为 0
            printThird.run();
            flag = 0;
            lock.notifyAll();
        }
    }
}

/**
 * CountDownLatch
 */
class Foo_2 {

    private final CountDownLatch c1 = new CountDownLatch(1);
    private final CountDownLatch c2 = new CountDownLatch(1);

    public Foo_2() {

    }

    public void first(Runnable printFirst) throws InterruptedException {
        // printFirst.run() outputs "first". Do not change or remove this line.
        printFirst.run();
        c1.countDown();
    }

    public void second(Runnable printSecond) throws InterruptedException {
        c1.await();
        // printSecond.run() outputs "second". Do not change or remove this line.
        printSecond.run();
        c2.countDown();
    }

    public void third(Runnable printThird) throws InterruptedException {
        c2.await();
        // printThird.run() outputs "third". Do not change or remove this line.
        printThird.run();
    }
}

/**
 * Semaphore
 */
class Foo_3 {

    private final Semaphore sp1 = new Semaphore(0);
    private final Semaphore sp2 = new Semaphore(0);

    public Foo_3() {

    }

    public void first(Runnable printFirst) throws InterruptedException {

        // printFirst.run() outputs "first". Do not change or remove this line.
        printFirst.run();
        sp1.release();
    }

    public void second(Runnable printSecond) throws InterruptedException {
        sp1.acquire();
        // printSecond.run() outputs "second". Do not change or remove this line.
        printSecond.run();
        sp2.release();
    }

    public void third(Runnable printThird) throws InterruptedException {
        sp2.acquire();
        // printThird.run() outputs "third". Do not change or remove this line.
        printThird.run();
    }
}