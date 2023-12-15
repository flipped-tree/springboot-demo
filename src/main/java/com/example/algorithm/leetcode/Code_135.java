package com.example.algorithm.leetcode;

/**
 * 异或
 * 1 ^ 1 = 0
 * 0 ^ 0 = 0
 * 1 ^ 0 = 1
 */
public class Code_135 {
    public static void main(String[] args) {
        int[] nums = {2,2,1};
        int single = singleNumber(nums);
        System.out.println(single);
    }

    private static int singleNumber(int[] nums){
        int singleNumber = 0;
        for (int num : nums) {
            singleNumber = singleNumber ^ num;
        }
        return singleNumber;
    }
}
