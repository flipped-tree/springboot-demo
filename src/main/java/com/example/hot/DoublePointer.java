package com.example.hot;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DoublePointer {

    public static void main(String[] args) {
        // 移动0
        int[] nums = {0, 1, 20, 0, 5, 3};
        moveZeroes(nums);
        System.out.println("move zero:" + Arrays.toString(nums));

        // 盛最多水的容器
        int[] height = {1, 8, 6, 2, 5, 4, 8, 3, 7};
        int area = maxArea(height);
        System.out.println("max area:" + area);

        // 三数之和
        int[] input = {-1, 0, 1, 2, -1, -4};
        List<List<Integer>> lists = threeSum(input);
        System.out.println("three sum:" + lists);
    }

    static List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums);
        int len = nums.length;
        for (int i = 0; i < len; i++) {
            if (nums[i] > 0) {
                break;
            }
            if (i > 0 && nums[i - 1] == nums[i]) {
                continue;
            }
            int left = i + 1;
            int right = len - 1;
            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];
                if (sum == 0) {
                    res.add(List.of(nums[i], nums[left], nums[right]));
                    while (left < right && nums[left] == nums[left + 1]) {
                        left++;
                    }
                    while (left < right && nums[right] == nums[right - 1]) {
                        right--;
                    }
                    left++;
                    right--;
                }
                if (sum < 0) {
                    left++;
                }
                if (sum > 0) {
                    right--;
                }
            }
        }
        return res;
    }

    static int maxArea(int[] height) {
        int maxArea = 0;
        int left = 0;
        int right = height.length - 1;
        while (left <= right) {
            int area = (right - left) * Math.min(height[left], height[right]);
            maxArea = Math.max(area, maxArea);
            if (height[left] <= height[right]) {
                left++;
            } else {
                right--;
            }
        }
        return maxArea;
    }

    static void moveZeroes(int[] nums) {
        int start = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                nums[start] = nums[i];
                start++;
            }
        }
        for (int i = start; i < nums.length; i++) {
            nums[i] = 0;
        }
    }
}
