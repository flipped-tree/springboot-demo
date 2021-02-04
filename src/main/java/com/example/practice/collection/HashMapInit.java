package com.example.practice.collection;

import java.util.HashMap;
import java.util.Map;

/**
 * @author xingce
 * @date 2020-06-25 18:01
 */
public class HashMapInit {

    public static void main(String[] args) {
        Map<String, String> map = new HashMap<>();
        map.put("1", "a");
        map.put("2", "b");
        map.put(null, "c");
        map.forEach((k, v) -> {
            System.out.println(k + "===" + v);
        });
        System.out.println(map.size());
    }

}
