package com.example.practice.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author xingce
 * @date 2020-04-21 14:08
 * <p>
 * 由于线程池中线程可回收，threadLocal线程局部变量如果不删除，会重新拿到其他线程中set的值
 */
public class ThreadLocalDemo {

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        ThreadA t = new ThreadA();
        executorService.execute(t);

        executorService.execute(t);

        executorService.execute(t);

        executorService.shutdown();
        executorService.awaitTermination(100, TimeUnit.SECONDS);
    }

    static class ThreadA implements Runnable {

        private final ThreadLocal<String> threadLocal = new ThreadLocal<>();

        private static int f = 0;

        @Override
        public void run() {
            if (f == 0) {
                threadLocal.set("A");
            }
            f++;
            try {
                Thread.sleep(1000L);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "-ThreadA输出：" + threadLocal.get());
        }
    }

    static class ThreadB implements Runnable {

        private final ThreadLocal<String> threadLocal = new ThreadLocal<>();

        @Override
        public void run() {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "ThreadB输出：" + threadLocal.get());
        }

    }

}
