package com.example.algorithm.leetcode;

import java.util.HashMap;
import java.util.Map;

public class Code_3 {
    public static void main(String[] args) {
        String str = "pwwkew";
        int length = getLengthOfLongestSubstring(str);
        System.out.println(length);
    }

    private static int getLengthOfLongestSubstring(String str) {
        int start = 0, longestLength = 0;
        Map<Character, Integer> map = new HashMap<>();
        for (int end = 0; end < str.length(); end++) {
            char c = str.charAt(end);
            if (map.containsKey(c)) {
                start = Math.max(start, map.get(c));
            }
            longestLength = Math.max(longestLength, end - start + 1);
            map.put(c, end + 1);
        }
        return longestLength;
    }
}
