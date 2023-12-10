package com.example.algorithm.leetcode;

public class Code_5 {
    public static void main(String[] args) {
        String str = "babad";
        System.out.println(longestPalindrome(str));
    }

    private static String longestPalindrome(String str) {
        int start = 0, end = 0;
        for (int i1 = 0; i1 < str.length(); i1++) {
            int l1 = getExpandLength(str, i1, i1);
            int l2 = getExpandLength(str, i1, i1 + 1);
            int maxLength = Math.max(l1, l2);
            if (maxLength > end - start) {
                start = i1 - (maxLength - 1) / 2;
                end = i1 + maxLength / 2;
            }
        }
        return str.substring(start, end + 1);
    }

    private static int getExpandLength(String str, int left, int right) {
        while (left >= 0 && right < str.length() && str.charAt(left) == str.charAt(right)) {
            left--;
            right++;
        }
        return right - left + 1;
    }
}
