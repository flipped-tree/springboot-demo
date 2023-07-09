package com.example.thread;

import java.util.concurrent.Semaphore;

public class SequencePrintC {

    private static final Semaphore semaphoreA = new Semaphore(1);
    private static final Semaphore semaphoreB = new Semaphore(0);
    private static final Semaphore semaphoreC = new Semaphore(0);

    public static void main(String[] args) {
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    semaphoreA.acquire();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.print("A");
                semaphoreB.release();
            }
        }).start();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    semaphoreB.acquire();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.print("B");
                semaphoreC.release();
            }
        }).start();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    semaphoreC.acquire();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.print("C");
                semaphoreA.release();
            }
        }).start();
    }

}
