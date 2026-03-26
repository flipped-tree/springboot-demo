package com.example.test;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 线程安全的内存键值对管理系统，支持过期时间
 *
 * @param <K> 键类型
 * @param <V> 值类型
 */
public class ExpiringKeyValueStore<K, V> {

    /**
     * 内部类：包装值和过期时间
     */
    private static class ExpiringEntry<V> {
        private final V value;
        private final long expireTime; // 过期时间戳（毫秒）

        public ExpiringEntry(V value, long ttl, TimeUnit unit) {
            this.value = value;
            this.expireTime = System.currentTimeMillis() + unit.toMillis(ttl);
        }

        public V getValue() {
            return value;
        }

        public boolean isExpired() {
            return System.currentTimeMillis() > expireTime;
        }

        public long getExpireTime() {
            return expireTime;
        }
    }

    // 存储数据的并发Map
    private final ConcurrentHashMap<K, ExpiringEntry<V>> map;

    // 定期清理过期数据的定时任务
    private final ScheduledExecutorService cleanupExecutor;

    // 是否已关闭
    private volatile boolean isShutdown = false;

    /**
     * 构造函数
     *
     * @param cleanupInterval 清理间隔时间
     * @param timeUnit        时间单位
     */
    public ExpiringKeyValueStore(long cleanupInterval, TimeUnit timeUnit) {
        this.map = new ConcurrentHashMap<>();

        // 创建定时清理任务
        this.cleanupExecutor = Executors.newSingleThreadScheduledExecutor(r -> {
            Thread t = new Thread(r, "ExpiringKeyValueStore-Cleanup");
            t.setDaemon(true); // 守护线程，不影响JVM退出
            return t;
        });

        // 定期清理过期数据
        this.cleanupExecutor.scheduleAtFixedRate(this::cleanupExpiredEntries, cleanupInterval, cleanupInterval, timeUnit);
    }

    /**
     * 默认构造函数（清理间隔60秒）
     */
    public ExpiringKeyValueStore() {
        this(60, TimeUnit.SECONDS);
    }

    /**
     * 设置键值对（永不过期）
     */
    public void put(K key, V value) {
        put(key, value, -1, TimeUnit.MILLISECONDS);
    }

    /**
     * 设置键值对（带过期时间）
     *
     * @param key   键
     * @param value 值
     * @param ttl   存活时间
     * @param unit  时间单位
     */
    public void put(K key, V value, long ttl, TimeUnit unit) {
        if (isShutdown) {
            throw new IllegalStateException("Store has been shutdown");
        }

        if (key == null) {
            throw new NullPointerException("Key cannot be null");
        }

        if (ttl <= 0) {
            // 永不过期
            map.put(key, new ExpiringEntry<>(value, Long.MAX_VALUE, TimeUnit.MILLISECONDS));
        } else {
            map.put(key, new ExpiringEntry<>(value, ttl, unit));
        }
    }

    /**
     * 获取值（如果已过期则返回null并删除）
     */
    public V get(K key) {
        if (key == null) {
            return null;
        }

        ExpiringEntry<V> entry = map.get(key);
        if (entry == null) {
            return null;
        }

        // 惰性删除：检查是否过期
        if (entry.isExpired()) {
            map.remove(key);
            return null;
        }

        return entry.getValue();
    }

    /**
     * 获取值（不检查过期时间，用于内部使用）
     */
    public V getWithoutCheck(K key) {
        if (key == null) {
            return null;
        }

        ExpiringEntry<V> entry = map.get(key);
        return entry != null ? entry.getValue() : null;
    }

    /**
     * 删除键
     *
     * @return 被删除的值，如果不存在返回null
     */
    public V remove(K key) {
        if (key == null) {
            return null;
        }

        ExpiringEntry<V> entry = map.remove(key);
        return entry != null ? entry.getValue() : null;
    }

    /**
     * 检查键是否存在（会检查过期时间）
     */
    public boolean containsKey(K key) {
        if (key == null) {
            return false;
        }

        ExpiringEntry<V> entry = map.get(key);
        if (entry == null) {
            return false;
        }

        if (entry.isExpired()) {
            map.remove(key);
            return false;
        }

        return true;
    }

    /**
     * 获取当前存储的元素数量（包含可能已过期的）
     */
    public int size() {
        return map.size();
    }

    /**
     * 获取实际有效的元素数量（排除已过期的）
     */
    public int validSize() {
        AtomicInteger count = new AtomicInteger(0);
        map.forEach((key, entry) -> {
            if (!entry.isExpired()) {
                count.incrementAndGet();
            }
        });
        return count.get();
    }

    /**
     * 清空所有数据
     */
    public void clear() {
        map.clear();
    }

    /**
     * 清理所有过期数据（手动触发）
     */
    public void cleanupExpiredEntries() {
        if (isShutdown) {
            return;
        }

        long now = System.currentTimeMillis();
        map.entrySet().removeIf(entry -> entry.getValue().getExpireTime() < now);
    }

    /**
     * 关闭存储系统（停止定时任务）
     */
    public void shutdown() {
        if (!isShutdown) {
            isShutdown = true;
            cleanupExecutor.shutdown();
            try {
                // 等待任务完成（最多5秒）
                if (!cleanupExecutor.awaitTermination(5, TimeUnit.SECONDS)) {
                    cleanupExecutor.shutdownNow();
                }
            } catch (InterruptedException e) {
                cleanupExecutor.shutdownNow();
                Thread.currentThread().interrupt();
            }
        }
    }

    /**
     * 获取所有键（排除已过期的）
     */
    public ConcurrentMap<K, V> getAllValidEntries() {
        ConcurrentHashMap<K, V> result = new ConcurrentHashMap<>();
        map.forEach((key, entry) -> {
            if (!entry.isExpired()) {
                result.put(key, entry.getValue());
            }
        });
        return result;
    }
}