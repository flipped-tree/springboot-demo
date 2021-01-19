package com.example.springboot.test;

import io.netty.util.concurrent.DefaultThreadFactory;

import java.io.File;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 生产者
 *
 * @author xingce
 * @date 2021/01/07 10:07
 */
public class Producer implements Runnable {

    private final String filePath;
    private final ThreadPoolExecutor executor;

    public Producer(String filePath, int threadSize) {
        this.filePath = filePath;
        executor = new ThreadPoolExecutor(threadSize, threadSize,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>(),
                new DefaultThreadFactory("producer"));
    }

    @Override
    public void run() {
        File[] listFiles = getFiles(filePath);
        if (listFiles == null || listFiles.length == 0) {
            System.out.println("目录下没有文件");
            return;
        }
        CountDownLatch countDownLatch = new CountDownLatch(listFiles.length);
        for (File file : listFiles) {
            FileReadThread fileReadThread = new FileReadThread();
            fileReadThread.setFileName(file.getAbsolutePath());
            fileReadThread.setCountDownLatch(countDownLatch);
            executor.execute(fileReadThread);
        }
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
        }
        executor.shutdown();
    }

    private File[] getFiles(String filePath) {
        File file = new File(filePath);
        return file.listFiles();
    }
}
