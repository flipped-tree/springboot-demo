package com.example.practice.readwritelock;

/**
 * @author xingce
 * @date 2019/12/27 11:29
 */
public class ReadWriteLockImpl implements ReadWriteLock {

    /**
     * 定义对象锁
     */
    private final Object MUTEX = new Object();
    /**
     * 当前有多少个线程写入
     */
    private int writingWriters = 0;
    /**
     * 当前多少个线程正在等待写入
     */
    private int waitingWriters = 0;
    /**
     * 当前多少个线程正在读
     */
    private int readingReaders = 0;
    /**
     * 是否偏好于写
     */
    private boolean preferWriter;

    /**
     * 不填默认为true
     */
    public ReadWriteLockImpl() {
        this(true);
    }

    public ReadWriteLockImpl(boolean preferWriter) {
        this.preferWriter = preferWriter;
    }

    @Override
    public Lock readLock() {
        return new ReadLock(this);
    }

    @Override
    public Lock writeLock() {
        return new WriteLock(this);
    }

    //正在写的线程增加1
    public void incrementWritingWriters() {
        this.writingWriters++;
    }

    //等待写的线程增加1
    public void incrementWaitingWriters() {
        this.waitingWriters++;
    }

    //正在读的线程增加1
    public void incrementReadingReaders() {
        this.readingReaders++;
    }

    //反之
    public void decrementWritingWriters() {
        this.writingWriters--;
    }

    public void decrementWaitingWriters() {
        this.waitingWriters--;
    }

    public void decrementReadingReaders() {
        this.readingReaders--;
    }

    @Override
    public int getWritingWriters() {
        return this.writingWriters;
    }

    @Override
    public int getWaitingWriters() {
        return this.waitingWriters;
    }

    @Override
    public int getReadingReaders() {
        return this.readingReaders;
    }

    //获取对象锁
    public Object getMUTEX() {
        return MUTEX;
    }

    //获取是否偏向写锁
    public boolean getPreferWriter() {
        return preferWriter;
    }

    //设置偏好锁
    public void setPreferWriter(boolean preferWriter) {
        this.preferWriter = preferWriter;
    }
}
