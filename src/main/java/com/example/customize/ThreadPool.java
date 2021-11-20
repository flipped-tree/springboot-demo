package com.example.customize;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.stream.IntStream;

/**
 * @author xingce
 * @date 2021/11/20 18:41
 */
public class ThreadPool {

    /**
     * 默认阻塞队列大小
     */
    private static final int DEFAULT_WORK_QUEUE_SIZE = 5;

    /**
     * 模拟实际的线程池使用阻塞队列来实现生产者-消费者模式
     */
    private final BlockingQueue<Runnable> workQueue;

    /**
     * 模拟实际的线程池使用List集合保存线程池内部的工作线程
     */
    private final List<WorkThread> workThreads = new ArrayList<>();

    class WorkThread extends Thread {
        @Override
        public void run() {
            // 不断循环获取队列中的任务
            while (true) {
                try {
                    Runnable workTask = workQueue.take();
                    workTask.run();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

    ThreadPool(int poolSize, BlockingQueue<Runnable> workQueue) {
        this.workQueue = workQueue;
        IntStream.range(0, poolSize).forEach((i) -> {
            WorkThread workThread = new WorkThread();
            workThread.start();
            workThreads.add(workThread);
        });
    }

    /**
     * 在ThreadPool的构造方法中传入线程池的大小
     */
    public ThreadPool(int poolSize) {
        this(poolSize, new LinkedBlockingQueue<>(DEFAULT_WORK_QUEUE_SIZE));
    }

    public void execute(Runnable task) {
        try {
            workQueue.put(task);
        } catch (InterruptedException e) {

        }
    }

    public static void main(String[] args) {
        ThreadPool threadPool = new ThreadPool(5);
        IntStream.range(0, 10).forEach((i) -> {
            threadPool.execute(() -> {
                System.out.println(Thread.currentThread().getName() + "--->> Hello ThreadPool");
            });
        });
    }
}
