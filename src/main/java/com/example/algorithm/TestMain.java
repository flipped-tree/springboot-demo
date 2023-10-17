package com.example.algorithm;

public class TestMain {
    public static void main(String[] args) {
        Thread t = new Thread(()->{
            System.out.println("new thread");
        });

        t.start();
        try {
            t.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("main thread");
    }
}
