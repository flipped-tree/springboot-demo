package com.example.springboot.practice.thread;

import java.util.concurrent.Semaphore;

public class LeetCodeThread2 {
    public static void main(String[] args) {
        FooBar_2 fooBar = new FooBar_2(6);
        new Thread(() -> {
            try {
                fooBar.foo(() -> {
                    System.out.print("foo");
                });
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(() -> {
            try {
                fooBar.bar(() -> {
                    System.out.println("bar");
                });
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }
}

class FooBar_1 {
    private int n;

    private boolean isChanged = true;

    public FooBar_1(int n) {
        this.n = n;
    }

    public void foo(Runnable printFoo) throws InterruptedException {

        for (int i = 0; i < n; i++) {
            synchronized (this) {
                if (!isChanged) {
                    this.wait();
                }
                // printFoo.run() outputs "foo". Do not change or remove this line.
                printFoo.run();
                isChanged = false;
                this.notifyAll();
            }
        }
    }

    public void bar(Runnable printBar) throws InterruptedException {

        for (int i = 0; i < n; i++) {
            synchronized (this) {
                if (isChanged) {
                    this.wait();
                }
                // printBar.run() outputs "bar". Do not change or remove this line.
                printBar.run();
                isChanged = true;
                this.notifyAll();
            }
        }
    }
}

class FooBar_2 {
    private int n;

    private final Semaphore s1 = new Semaphore(1);
    private final Semaphore s2 = new Semaphore(0);

    public FooBar_2(int n) {
        this.n = n;
    }

    public void foo(Runnable printFoo) throws InterruptedException {

        for (int i = 0; i < n; i++) {
            s1.acquire();
            // printFoo.run() outputs "foo". Do not change or remove this line.
            printFoo.run();
            s2.release();
        }
    }

    public void bar(Runnable printBar) throws InterruptedException {

        for (int i = 0; i < n; i++) {
            s2.acquire();
            // printBar.run() outputs "bar". Do not change or remove this line.
            printBar.run();
            s1.release();
        }
    }
}
