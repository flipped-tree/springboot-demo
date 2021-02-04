package com.example.practice.algorithm.leetcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class TwoSum {

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6};
        System.out.println(Arrays.toString(getIndex(arr, 4)));
    }

    private static int[] getIndex(int[] arr, int target) {
        int length = arr.length;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < arr.length - 1; i++) {
            if (map.containsKey(target - arr[i])) {
                return new int[]{map.get(target - arr[i]), i};
            }
            map.put(arr[i], i);
        }
        return new int[]{0};
    }

}
