package com.example.springboot.practice.lock;

/**
 * @author xingce
 * @date 2020-06-29 15:14
 */
public class ReadWriteLockDemo {

    public static void main(String[] args) {
        ReentrantReadWriteLockCache cache = new ReentrantReadWriteLockCache();
        cache.put("1", "a");
    }

}
