package com.example.algorithm.leetcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Code_1 {
    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4, 5, 6, 7, 8};
        int target = 9;
        int[] ints = twoSum(nums, target);
        System.out.println(Arrays.toString(ints));
    }

    private static int[] twoSum(int[] nums, int target) {
        int length = nums.length;
        if (length == 0) {
            return new int[0];
        }
        Map<Integer, Integer> map = new HashMap<>();
        for (int start = 0; start < length; start++) {
            if (map.containsKey(target - nums[start])) {
                return new int[]{map.get(target - nums[start]), start};
            }
            map.put(nums[start], start);
        }
        return new int[0];
    }
}
