package com.example.springboot.practice.thread;

import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

public class LeetCodeThread4 {
    public static void main(String[] args) {
        H2O h2o = new H2O();
        H2O h2O = new H2O();
        int n = 5;
        new Thread(() -> {
            for (int i = 0; i < 2 * n; i++) {
                try {
                    h2O.hydrogen(() -> System.out.print("H"));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        new Thread(() -> {
            for (int i = 0; i < n; i++) {
                try {
                    h2O.oxygen(() -> System.out.print("O"));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}

class H2O {
    private final Semaphore semaH = new Semaphore(2);
    private final Semaphore semaO = new Semaphore(1);

    private final AtomicInteger groupCount = new AtomicInteger(0);

    private static final int GROUP_H_LIMIT = 2;
    private static final int GROUP_O_LIMIT = 1;
    private static final int GROUP_TOTAL_LIMIT = GROUP_H_LIMIT + GROUP_O_LIMIT;

    public H2O() {
    }

    public void hydrogen(Runnable releaseHydrogen) throws InterruptedException {
        // Try to acquire hygrogen permit.
        this.semaH.acquire(1);

        // releaseHydrogen.run() outputs "H". Do not change or remove this line.
        releaseHydrogen.run();

        // Increment group molecule count.
        this.groupCount.incrementAndGet();

        resetIfNeeded();
    }

    public void oxygen(Runnable releaseOxygen) throws InterruptedException {
        this.semaO.acquire(1);

        // releaseOxygen.run() outputs "O". Do not change or remove this line.
        releaseOxygen.run();

        this.groupCount.incrementAndGet();

        resetIfNeeded();
    }

    private void resetIfNeeded() {
        if (this.groupCount.compareAndSet(GROUP_TOTAL_LIMIT, 0)) {
            this.semaH.release(GROUP_H_LIMIT);
            this.semaO.release(GROUP_O_LIMIT);
        }
    }
}