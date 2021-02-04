package com.example.practice.thread;

/**
 * thread start() method test
 * <p>
 * JVM 内存happens-before原则
 *
 * @author xingce
 * @date 2019/11/21 14:44
 */
public class StartMethodTest {
    public static void main(String[] args) {
        Thread t = new Thread(StartMethodTest::pong);
        t.start();
        System.out.println("ping");
    }

    private static void pong() {
        System.out.println("pong");
    }
}
