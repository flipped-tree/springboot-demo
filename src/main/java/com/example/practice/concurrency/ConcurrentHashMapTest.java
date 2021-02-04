package com.example.practice.concurrency;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author xingce
 * @date 2020-06-17 10:20
 */
public class ConcurrentHashMapTest {

    public static void main(String[] args) {
        Map<String, Integer> map = new ConcurrentHashMap<>(4);
        map.put("12", 1);
        map.size();
    }

}
