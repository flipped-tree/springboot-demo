package com.example.practice.concurrency;

import java.util.HashMap;
import java.util.Map;

public class HashMapTest {

    public static void main(String[] args) {
        Map<String, Integer> map = new MyHashMap();
        map.put("111", 1);
    }

    static class MyHashMap extends HashMap {

        MyHashMap() {
            new HashMap<>(8, 0.75f);
        }

        @Override
        public Object put(Object key, Object value) {
            return super.put(key, value);
        }
    }

}
