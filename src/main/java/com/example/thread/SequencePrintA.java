package com.example.thread;

/**
 * Thread join()方法顺序打印A B C
 *
 * @author xingce
 * @date 2019/11/21 15:15
 */
public class SequencePrintA {
    public static void main(String[] args) {
        Thread a = new Thread(() -> System.out.println("A"));
        Thread b = new Thread(() -> {
            try {
                a.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("B");
        });
        Thread c = new Thread(() -> {
            try {
                b.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("C");
        });

        a.start();
        b.start();
        c.start();
    }
}
