package com.example.practice.readwritelock;

/**
 * @author xingce
 * @date 2019/12/27 11:26
 */
public interface ReadWriteLock {
    Lock readLock();

    Lock writeLock();

    /**
     * 获取正在写入中的线程数(只能为0和1)
     *
     * @return
     */
    int getWritingWriters();

    /**
     * 获取等待写入的线程数
     *
     * @return
     */
    int getWaitingWriters();

    /**
     * 获取正在读取的线程数
     *
     * @return
     */
    int getReadingReaders();

    static ReadWriteLock readWriterLock() {
        return new ReadWriteLockImpl();
    }

    static ReadWriteLock readWriterLock(boolean preferWriter) {
        return new ReadWriteLockImpl(preferWriter);
    }
}
