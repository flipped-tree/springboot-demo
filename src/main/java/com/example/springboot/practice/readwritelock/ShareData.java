package com.example.springboot.practice.readwritelock;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * @author xingce
 * @date 2019/12/27 19:05
 */
public class ShareData {
    private final List<Character> container = new ArrayList<>();
    private final ReadWriteLock readWriteLock = ReadWriteLock.readWriterLock();
    private final Lock readLock = readWriteLock.readLock();
    private final Lock writeLock = readWriteLock.writeLock();
    private final int length;

    public ShareData(int length) {
        this.length = length;
        IntStream.range(0, length).forEach(i -> {
            container.add(i, 'a');
        });
    }

    public char[] read() throws InterruptedException {
        try {
            //创建读锁
            readLock.lock();
            char[] newBuffer = new char[length];
            IntStream.range(0, length).forEach(i -> {
                newBuffer[i] = container.get(i);
            });
            slowly();
            return newBuffer;
        } finally {
            //解锁
            readLock.unlock();
        }
    }

    public void write(char a) throws InterruptedException {
        try {
            writeLock.lock();
            IntStream.range(0, length).forEach(i -> {
                container.add(i, a);
            });
            slowly();
        } finally {
            writeLock.unlock();
        }
    }

    //模拟读取时间
    private void slowly() {

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
