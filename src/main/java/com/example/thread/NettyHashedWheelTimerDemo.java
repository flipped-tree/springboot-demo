package com.example.thread;

import io.netty.util.HashedWheelTimer;
import io.netty.util.Timeout;
import io.netty.util.TimerTask;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;

/**
 * Netty 时间轮（HashedWheelTimer）完整示例
 * <p>
 * 这个示例展示：
 * 1) 如何创建时间轮
 * 2) 如何提交延时任务
 * 3) 如何取消任务
 * 4) 如何优雅关闭时间轮
 */
public class NettyHashedWheelTimerDemo {

    // 仅用于打印更直观的时间
    private static final DateTimeFormatter TIME_FMT = DateTimeFormatter.ofPattern("HH:mm:ss.SSS");

    public static void main(String[] args) throws InterruptedException {

        /*
         * 一、创建时间轮
         *
         * 参数说明（常用构造）：
         * - tickDuration：每个槽（tick）代表的时间长度
         * - unit：时间单位
         * - ticksPerWheel：一圈有多少个槽，建议 2 的幂（Netty 内部也会做规整）
         *
         * 例如这里：
         * tickDuration = 100ms, ticksPerWheel = 512
         * 一圈总时长约 = 100ms * 512 = 51.2s
         *
         * 说明：
         * - 时间轮是“近似定时”，不是绝对精准到毫秒。
         * - 一个应用中通常应尽量复用少量 timer 实例，避免创建太多线程。
         */
        HashedWheelTimer timer = new HashedWheelTimer(
                100, TimeUnit.MILLISECONDS, 512
        );

        log("程序启动，准备提交任务...");

        /*
         * 二、提交任务
         *
         * newTimeout(task, delay, unit):
         * - task：到期后执行的回调
         * - delay：延迟时间
         * - 返回 Timeout，可用于取消任务或查看状态
         */
        Timeout t1 = timer.newTimeout(new SimpleTask("任务A：2秒后执行"), 2, TimeUnit.SECONDS);
        Timeout t2 = timer.newTimeout(new SimpleTask("任务B：5秒后执行"), 5, TimeUnit.SECONDS);
        Timeout t3 = timer.newTimeout(new SimpleTask("任务C：10秒后执行（会被取消）"), 10, TimeUnit.SECONDS);

        /*
         * 三、演示取消任务
         *
         * cancel() 返回 true 表示取消成功；
         * 返回 false 可能是任务已执行、已取消，或者状态不允许取消。
         */
        Thread.sleep(3000); // 主线程先睡 3 秒，让任务A有机会执行
        boolean cancelResult = t3.cancel();
        log("尝试取消任务C，结果 = " + cancelResult);

        /*
         * 四、再提交一个“链式任务”示例
         *
         * 任务D执行后再动态创建一个任务D-1，模拟重试/超时链路场景。
         */
        timer.newTimeout(timeout -> {
            log("任务D：3秒后执行，随后再创建一个子任务D-1（1秒后）");

            timer.newTimeout(
                    new SimpleTask("任务D-1：由任务D动态创建，1秒后执行"),
                    1, TimeUnit.SECONDS
            );
        }, 3, TimeUnit.SECONDS);

        /*
         * 让主线程多等一会，保证能看到任务输出。
         */
        Thread.sleep(8000);

        /*
         * 五、优雅关闭时间轮
         *
         * stop() 会停止 worker 线程，并返回“未执行的任务集合”。
         * 实际生产中可以把这些未执行任务做日志记录或转储处理。
         */
        var unprocessed = timer.stop();
        log("时间轮已停止，未处理任务数量 = " + unprocessed.size());

        /*
         * 注意：
         * HashedWheelTimer 不建议反复 stop 后再复用。
         * 一般作为应用生命周期内的长寿命组件使用。
         */
    }

    /**
     * 一个简单的 TimerTask 实现：到期后打印日志。
     */
    static class SimpleTask implements TimerTask {
        private final String message;

        SimpleTask(String message) {
            this.message = message;
        }

        @Override
        public void run(Timeout timeout) {
            /*
             * timeout.isCancelled()：任务是否已取消
             * timeout.isExpired()：任务是否已到期（执行后通常为 true）
             */
            log(message
                    + " | isCancelled=" + timeout.isCancelled()
                    + " | isExpired=" + timeout.isExpired());
        }
    }

    private static void log(String msg) {
        System.out.println("[" + LocalTime.now().format(TIME_FMT) + "] " + msg);
    }
}