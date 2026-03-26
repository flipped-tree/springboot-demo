package com.example.hot;

import java.util.*;

public class ScrollWindow {

    public static void main(String[] args) {
        // 无重复字符的最长子串
        String s = "abcabcbb";
        int i = longestOfLongestSubstring(s);
        System.out.println(i);

        // 找到字符串中的所有字符异位词
        String sz = "cbaebabacd", p = "abc";
        List<Integer> anagrams = findAnagrams(sz, p);
        System.out.println(anagrams);
    }

    static List<Integer> findAnagrams(String s, String p) {
        List<Integer> res = new ArrayList<>();
        int pLen = p.length();
        int[] pCount = new int[26];
        for (int i = 0; i < pLen; i++) {
            pCount[p.charAt(i) - 'a']++;
        }
        int[] sCount = new int[26];
        for (int right = 0; right < s.length(); right++) {
            sCount[s.charAt(right) - 'a']++;
            int left = right - pLen + 1;
            if (left < 0) {
                continue;
            }
            if (Arrays.equals(sCount, pCount)) {
                res.add(left);
            }
            sCount[s.charAt(left) - 'a']--;
        }
        return res;
    }

    static int longestOfLongestSubstring(String s) {
        int left = 0;
        Set<Character> memo = new HashSet<>();
        int length = 0;
        for (int right = 0; right < s.length(); right++) {
            if (memo.contains(s.charAt(right))) {
                memo.remove(s.charAt(left));
                left++;
            }
            memo.add(s.charAt(right));
            length = Math.max(length, right - left + 1);
        }
        return length;
    }
}
