package com.example.springboot.practice.readwritelock;

/**
 * @author xingce
 * @date 2019/12/27 11:25
 */
public interface Lock {
    /**
     * 加锁
     */
    void lock() throws InterruptedException;

    /**
     * 释放锁
     */
    void unlock();
}
