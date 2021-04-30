package com.example.thread;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author xingce
 * @date 2021/3/6 13:20
 * 判断线程池是否结束
 * 线程池拒绝策略后无法正常关闭
 */
public class ThreadPoolTest {
    public static void main(String[] args) {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(3, 3, 1, TimeUnit.SECONDS, new LinkedBlockingQueue<>(2)
                , new NamedThreadFactory("test-pool"), (r, executor1) -> {
            try {
                System.out.println("重新放进线程池");
                executor1.getQueue().put(r);
            } catch (InterruptedException e) {
            }
        });
        long start = System.currentTimeMillis();
        AtomicInteger ai = new AtomicInteger(0);
        for (int i = 0; i < 10; i++) {
            executor.submit(() -> {
                int threadSize = executor.getActiveCount();
                int queueCurrentSize = executor.getQueue().size();
                System.out.println(Thread.currentThread().getName() + "当前线程数：" + threadSize + "当前队列大小：" + queueCurrentSize + "执行次数：" + ai.incrementAndGet());
            });
        }

        executor.shutdown();
        while (true) {
            if (executor.isTerminated()) {
                System.out.println("线程池使用时间：" + (System.currentTimeMillis() - start));
                break;
            }
        }
    }

    private static class NamedThreadFactory implements ThreadFactory {
        private static final AtomicInteger POOL_NUMBER = new AtomicInteger(1);
        private final ThreadGroup group;
        private final AtomicInteger threadNumber = new AtomicInteger(1);
        private final String namePrefix;

        public NamedThreadFactory(String name) {
            SecurityManager s = System.getSecurityManager();
            group = (s != null) ? s.getThreadGroup() : Thread.currentThread().getThreadGroup();
            namePrefix = "pool-" + POOL_NUMBER.getAndIncrement() + "-" + name + "-thread-";
        }

        @Override
        public Thread newThread(Runnable r) {
            Thread t = new Thread(group, r, namePrefix + threadNumber.getAndIncrement(), 0);
            if (t.isDaemon()) {
                t.setDaemon(false);
            }
            if (t.getPriority() != Thread.NORM_PRIORITY) {
                t.setPriority(Thread.NORM_PRIORITY);
            }
            return t;
        }

    }
}
