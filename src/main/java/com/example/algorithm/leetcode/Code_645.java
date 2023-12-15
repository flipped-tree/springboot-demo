package com.example.algorithm.leetcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Code_645 {
    public static void main(String[] args) {
        int[] nums = {1, 2, 2, 4};
        int[] errorNums = findErrorNums(nums);
        System.out.println(Arrays.toString(errorNums));
    }

    private static int[] findErrorNums(int[] nums) {
        int n = nums.length;
        int[] errorNums = new int[2];
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        for (int i = 0; i <= n; i++) {
            int count = map.getOrDefault(i, 0);
            if (count == 2) {
                errorNums[0] = i;
            } else if (count == 0) {
                errorNums[1] = i;
            }
        }
        return errorNums;
    }
}
