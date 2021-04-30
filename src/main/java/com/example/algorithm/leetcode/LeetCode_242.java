package com.example.algorithm.leetcode;

/**
 * @author xingce
 * @date 2020/12/11 15:24
 */
public class LeetCode_242 {

    public static void main(String[] args) {
        System.out.println(isAnagram("", ""));
    }

    public static boolean isAnagram(String s, String t) {
        if (s.length() == 0 || t.length() == 0) {
            return false;
        }
        int[] alphabet = new int[26];
        for (char c : s.toCharArray()) {
            alphabet[c - 'a']++;
        }
        for (char c : t.toCharArray()) {
            alphabet[c - 'a']--;
        }
        for (int num : alphabet) {
            if (num != 0) {
                return false;
            }
        }
        return true;
    }
}
