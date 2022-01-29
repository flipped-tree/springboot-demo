package com.example.thread;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 判断线程池是否结束
 * 线程池拒绝策略后无法正常关闭
 * <p>
 * 线程池通过32位长度二进制进行线程状态和数量的判断
 * 高3位表示状态 低29位标识数量
 *
 * @author xingce
 * @date 2021/3/6 13:20
 */
public class ThreadPoolExecutorDemo {

    public static void main(String[] args) {
        /*
         * @corePoolSize 线程池中一直存在的线程数，即使线程处于空闲状态，除非设置 allowCoreThreadTimeOut
         * @maximumPooSize 线程池可允许的最大线程数
         * @keepAliveTime 线程数大于核心线程数时，这是多余的空闲线程在中止之前等待新任务的最大时长
         * @unit keepALiveTime的时间单位
         * @workQueue 在任务之前之前保存任务的队列，这个队列只保存通过execute方法提交的Runnable任务
         * @threadFactory 线程工厂，生成线程
         * @handler executor由于线程临界和队列达到最大容量异常而触发的处理器
         *      AbortPolicy 直接抛弃，抛出异常
         *      CallerRunsPolicy 用调用者的线程执行任务
         *      DisCardOldestPolicy 抛弃队列中最久的任务
         *      DiscardPolicy 抛弃当前任务
         */
        ThreadPoolExecutor executor = new ThreadPoolExecutor(3,
                5,
                1,
                TimeUnit.SECONDS, new
                LinkedBlockingQueue<>(2),
                new NamedThreadFactory("test-pool"),
                (r, poolExecutor) -> {
                    try {
                        System.out.println("重新放进线程池");
                        poolExecutor.getQueue().put(r);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                });
        long start = System.currentTimeMillis();
        AtomicInteger ai = new AtomicInteger(0);
        for (int i = 0; i < 10; i++) {
            executor.execute(() -> {
                try {
                    Thread.sleep(5L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                int threadSize = executor.getActiveCount();
                int queueCurrentSize = executor.getQueue().size();
                System.out.println(Thread.currentThread().getName() + "；当前线程数：" + threadSize + "；当前队列大小：" + queueCurrentSize + "；执行次数：" + ai.incrementAndGet());
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
