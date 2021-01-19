package com.example.springboot.practice.concurrency;

/**
 * @author xingce
 * @date 2019/12/12 16:19
 */
public class TraditionalThread {

    public static void main(String[] args) {
        new Thread(() -> {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Runnable:" + Thread.currentThread().getName());
        }) {
            @Override
            public void run() {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Thread:" + Thread.currentThread().getName());
            }
        }.start();
    }

}
