package com.example.algorithm.leetcode;

public class Code_9 {
    public static void main(String[] args) {
        System.out.println(isPalindrome(121));
        System.out.println(isPalindrome(-121));
        System.out.println(isPalindrome(1221));
    }

    private static boolean isPalindrome(int input) {
        if (input < 0) {
            return false;
        }
        int temp = input, result = 0;
        while (input != 0) {
            result = result * 10 + input % 10;
            input = input / 10;
        }
        return temp == result;
    }
}
