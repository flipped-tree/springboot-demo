package com.example.practice.thread;

import java.util.concurrent.*;

/**
 * @author xingce
 * @date 2020/12/17 20:25
 */
public class ThreadCreateDemo {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.execute(new TestThread());
        executorService.execute(new TestRunnable());

        Future<Integer> future = executorService.submit(new TestCallable());
        System.out.println(future.get());

        executorService.shutdown();
        executorService.awaitTermination(1000, TimeUnit.SECONDS);
    }
}

class TestThread extends Thread {
    @Override
    public void run() {
        System.out.println("test thread pool using Thread class");
    }
}

class TestRunnable implements Runnable {

    @Override
    public void run() {
        System.out.println("test thread pool using Runnable class");
    }
}

class TestCallable implements Callable<Integer> {

    @Override
    public Integer call() {
        System.out.println("test thread pool using Callable class");
        return 12;
    }
}