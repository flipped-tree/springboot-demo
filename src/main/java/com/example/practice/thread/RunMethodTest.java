package com.example.practice.thread;

/**
 * thread run() method test
 * <p>
 * happens-before rule
 *
 * @author xingce
 * @date 2019/11/21 14:50
 */
public class RunMethodTest {
    public static void main(String[] args) {
        Thread t = new Thread(RunMethodTest::pong);
        t.start();
        System.out.println("ping");
    }

    private static void pong() {
        System.out.println("pong");
    }
}
