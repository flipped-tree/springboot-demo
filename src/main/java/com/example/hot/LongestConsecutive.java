package com.example.hot;

import java.util.HashSet;
import java.util.Set;

public class LongestConsecutive {

    public static void main(String[] args) {
        int[] nums = { 100, 4, 200, 1, 3, 2 };
        int result = longestConsecutive(nums);
        System.out.println(result);
    }

    public static int longestConsecutive(int[] nums) {
        int ans = 0;
        if (nums == null || nums.length == 0) {
            return ans;
        }
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }
        for (int num : nums) {
            if (set.contains(num - 1)) {
                continue;
            }
            // num is the start of the sequence
            int nextSeq = num + 1;
            while (set.contains(nextSeq)) {
                nextSeq++;
            }
            ans = Math.max(ans, nextSeq - num);
        }
        return ans;
    }
}
