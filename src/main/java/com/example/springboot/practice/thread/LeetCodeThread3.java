package com.example.springboot.practice.thread;

import java.util.concurrent.Semaphore;
import java.util.function.IntConsumer;

public class LeetCodeThread3 {
    public static void main(String[] args) {
        ZeroEvenOdd_2 zeroEvenOdd = new ZeroEvenOdd_2(6);
        new Thread(() -> {
            try {
                zeroEvenOdd.zero(System.out::print);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        new Thread(() -> {
            try {
                zeroEvenOdd.even(System.out::print);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        new Thread(() -> {
            try {
                zeroEvenOdd.odd(System.out::print);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }
}

class ZeroEvenOdd_1 {
    private final int n;
    private final Semaphore zero = new Semaphore(1);
    private final Semaphore even = new Semaphore(0);
    private final Semaphore odd = new Semaphore(0);

    public ZeroEvenOdd_1(int n) {
        this.n = n;
    }

    public void zero(IntConsumer printNumber) throws InterruptedException {
        for (int i = 1; i <= n; i++) {
            zero.acquire();
            printNumber.accept(0);
            if (i % 2 == 1) {
                odd.release();
            } else {
                even.release();
            }
        }
    }

    /**
     * 偶数
     */
    public void even(IntConsumer printNumber) throws InterruptedException {
        for (int i = 2; i <= n; i += 2) {
            even.acquire();
            printNumber.accept(i);
            zero.release();
        }
    }

    /**
     * 奇数
     */
    public void odd(IntConsumer printNumber) throws InterruptedException {
        for (int i = 1; i <= n; i += 2) {
            odd.acquire();
            printNumber.accept(i);
            zero.release();
        }
    }


}


class ZeroEvenOdd_2 {
    private int n;

    private int state;

    private boolean control = true;

    public ZeroEvenOdd_2(int n) {
        this.n = n;
    }

    public void zero(IntConsumer printNumber) throws InterruptedException {
        for (int i = 0; i < n; i++) {
            synchronized (this) {
                while (state != 0) {
                    this.wait();
                }
                printNumber.accept(0);
                if (control) {
                    state = 1;
                } else {
                    state = 2;
                }
                this.notifyAll();
            }
        }
    }

    public void even(IntConsumer printNumber) throws InterruptedException {
        for (int i = 2; i <= n; i += 2) {
            synchronized (this) {
                while (state != 2) {
                    this.wait();
                }
                printNumber.accept(i);
                control = true;
                state = 0;
                this.notifyAll();
            }
        }
    }

    public void odd(IntConsumer printNumber) throws InterruptedException {
        for (int i = 1; i <= n; i += 2) {
            synchronized (this) {
                while (state != 1) {
                    this.wait();
                }
                printNumber.accept(i);
                control = false;
                state = 0;
                this.notifyAll();
            }
        }
    }
}