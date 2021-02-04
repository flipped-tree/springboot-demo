package com.example.practice.readwritelock;

/**
 * @author xingce
 * @date 2019/12/27 19:00
 */
public class WriteLock implements Lock {

    private final ReadWriteLockImpl readWriteLockImpl;

    public WriteLock(ReadWriteLockImpl readWriteLockImpl) {
        this.readWriteLockImpl = readWriteLockImpl;
    }

    @Override
    public void lock() throws InterruptedException {
        synchronized (readWriteLockImpl.getMUTEX()) {
            try {
                readWriteLockImpl.incrementWaitingWriters();
                while (readWriteLockImpl.getReadingReaders() > 0 ||
                        readWriteLockImpl.getWritingWriters() > 0) {
                    readWriteLockImpl.getMUTEX().wait();
                }
            } finally {
                readWriteLockImpl.decrementWaitingWriters();
            }
            readWriteLockImpl.incrementWritingWriters();
        }
    }

    @Override
    public void unlock() {
        synchronized (readWriteLockImpl.getMUTEX()) {
            readWriteLockImpl.decrementWritingWriters();
            readWriteLockImpl.setPreferWriter(false);
            readWriteLockImpl.getMUTEX().notifyAll();
        }
    }
}
