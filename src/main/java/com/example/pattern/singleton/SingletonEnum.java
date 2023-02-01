package com.example.pattern.singleton;

/**
 * @author xingce
 * @date 2021/03/26 22:52
 */
public enum SingletonEnum {
    /**
     * 枚举实现单例模式
     */
    INSTANCE;

    private final Singleton instance;

    SingletonEnum() {
        instance = new Singleton();
    }

    public Singleton getInstance() {
        return instance;
    }
}
