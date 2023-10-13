package com.example.pattern.flyweight;

import java.util.HashMap;
import java.util.Map;

public class BigCharFactory {
    // 管理已经生成的BigChar的实例
    private final Map<String, BigChar> pool = new HashMap<>();
    // Singleton模式
    private static final BigCharFactory singleton = new BigCharFactory();

    // 构造函数
    private BigCharFactory() {
    }

    // 获取唯一的实例
    public static BigCharFactory getInstance() {
        return singleton;
    }

    // 生成（共享）BigChar类的实例
    public synchronized BigChar getBigChar(char charName) {
        BigChar bc = pool.get("" + charName);
        if (bc == null) {
            bc = new BigChar(charName); // 生成BigChar的实例
            pool.put("" + charName, bc);
        }
        return bc;
    }
}
