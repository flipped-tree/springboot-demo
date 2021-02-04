package com.example.practice.lock;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author xingce
 * @date 2020-06-29 15:07
 */
public class ReentrantReadWriteLockCache {

    static Map<String, Object> map = new HashMap<>();

    static ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    static Lock readLock = readWriteLock.readLock();

    static Lock writeLock = readWriteLock.writeLock();

    static Lock secondLock = readWriteLock.writeLock();

    public final Object get(String key) {
        readLock.lock();
        try {
            return map.get(key);
        } finally {
            readLock.unlock();
        }
    }

    public final Object put(String key, Object value) {
        writeLock.lock();
        try {
            secondLock.lock();
            try {
                return map.put(key, value);
            } finally {
                secondLock.unlock();
            }
        } finally {
            writeLock.unlock();
        }
    }

    public final Object getObj(String k) {
        Object obj = null;
        readLock.lock();
        try {
            // 获取缓存中的值
            obj = map.get(k);
        } finally {
            readLock.unlock();
        }
        // 缓存中值不为空，直接返回
        if (obj != null) {
            return obj;
        }

        // 缓存中值为空，则通过写锁查询DB，并将其写入到缓存中
        writeLock.lock();
        try {
            // 再次尝试获取缓存中的值
            obj = map.get(k);
            // 再次获取缓存中值还是为空
            if (obj == null) {
                // 查询DB
                obj = getDataFromDb(k); // 伪代码：getDataFromDB
                // 将其放入到缓存中
                map.put(k, obj);
            }
        } finally {
            writeLock.unlock();
        }
        return obj;
    }

    private Object getDataFromDb(String k) {
        return new Object();
    }

}
