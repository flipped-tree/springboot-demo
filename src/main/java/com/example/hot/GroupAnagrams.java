package com.example.hot;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class GroupAnagrams {

    public static void main(String[] args) {
        String[] strings = { "eat", "tea", "tan", "ate", "nat", "bat" };
        List<List<String>> lists = groupAnagrams(strings);
        System.out.println(lists);
    }

    private static List<List<String>> groupAnagrams(String[] strs) {
        if (strs == null || strs.length == 0) {
            return new ArrayList<>();
        }
        Map<String, List<String>> result = new HashMap<>();
        for (String str : strs) {
            char[] array = str.toCharArray();
            Arrays.sort(array);
            String s = new String(array);
            if (result.containsKey(s)) {
                result.get(s).add(str);
            } else {
                List<String> list = new ArrayList<>();
                list.add(str);
                result.put(s, list);
            }
        }
        return new ArrayList<>(result.values());
    }
}