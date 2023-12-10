package com.example.algorithm.leetcode;

import java.util.Arrays;

public class Code_14 {
    public static void main(String[] args) {
        String[] strings = {"flower", "flow", "flight"};
        System.out.println(getCommonPrefix(strings));
        System.out.println(getPrefix(strings));
    }

    private static String getCommonPrefix(String[] strings) {
        if (strings.length == 0) {
            return "";
        }
        String prefix = strings[0];
        for (int i = 1; i < strings.length; i++) {
            while (strings[i].indexOf(prefix) != 0) {
                prefix = prefix.substring(0, prefix.length() - 1);
                if (prefix.isEmpty()) {
                    return "";
                }
            }
        }
        return prefix;
    }

    private static String getPrefix(String[] strings) {
        if (strings.length == 0) {
            return "";
        }
        Arrays.sort(strings);
        String start = strings[0], end = strings[strings.length - 1];
        int length = Math.min(start.length(), end.length());
        int i = 0;
        while (i < length && start.charAt(i) == end.charAt(i)) {
            i++;
        }
        return start.substring(0, i);
    }
}
