package com.example.hot;

public class Array {

    static int maxSubArray(int[] nums) {
        int n = nums.length;
        int[] sum = new int[n + 1];
        sum[0] = nums[0];
        for (int i = 1; i < n; i++) {
            if (sum[i - 1] > 0) {
                sum[i] = sum[i - 1] + nums[i];
            } else {
                sum[i] = nums[i];
            }
        }
        int res = 0;
        for (int j : sum) {
            res = Math.max(res, j);
        }
        return res;
    }
}
