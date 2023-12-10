package com.example.algorithm.leetcode;

public class Code_11 {
    public static void main(String[] args) {
        int[] height = {1, 8, 6, 2, 5, 4, 8, 3, 7};
        System.out.println(maxArea(height));
    }

    private static int maxArea(int[] height) {
        int start = 0, end = height.length - 1, sum = 0;
        while (start < end) {
            sum = height[start] < height[end] ?
                    Math.max(sum, height[start++] * (end - start)) :
                    Math.max(sum, height[end--] * (end - start));
        }
        return sum;
    }
}
