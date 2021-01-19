package com.example.springboot.practice.readwritelock;

/**
 * @author xingce
 * @date 2019/12/27 18:51
 */
public class ReadLock implements Lock {

    private final ReadWriteLockImpl readWriteLockImpl;

    public ReadLock(ReadWriteLockImpl readWriteLockImpl) {
        this.readWriteLockImpl = readWriteLockImpl;
    }

    @Override
    public void lock() throws InterruptedException {
        synchronized (readWriteLockImpl.getMUTEX()) {
            while (readWriteLockImpl.getWaitingWriters() > 0 || (
                    readWriteLockImpl.getPreferWriter() && readWriteLockImpl.getWaitingWriters() > 0)) {
                readWriteLockImpl.getMUTEX().wait();
            }
            readWriteLockImpl.incrementReadingReaders();
        }
    }

    @Override
    public void unlock() {
        synchronized (readWriteLockImpl.getMUTEX()) {
            readWriteLockImpl.decrementReadingReaders();
            readWriteLockImpl.setPreferWriter(true);
            readWriteLockImpl.getMUTEX().notifyAll();
        }
    }
}
