package com.example.hot;

import java.util.*;

public class HashTest {

    public static void main(String[] args) {
        // 两数之和
        int[] nums = {1, 2, 3, 4, 5, 6, 7};
        int[] ints = twoSum(nums, 7);
        System.out.println("two sum:" + Arrays.toString(ints));

        // 字母异位词分组
        String[] strs = {"eat", "tea", "tan", "ate", "nat", "bat"};
        List<List<String>> lists = groupAnagrams(strs);
        System.out.println("group anagrams:" + lists);

        // 最长连续子序列
        int[] inputs = {1, 2, 3, 5, 6, 7, 8, 10};
        int i = longestConsecutive(inputs);
        System.out.println("longest consecutive:" + i);
    }

    static List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> res = new ArrayList<>();
        if (strs == null || strs.length == 0) {
            return res;
        }
        Map<String, List<String>> memo = new HashMap<>();
        for (String str : strs) {
            char[] array = str.toCharArray();
            Arrays.sort(array);
            String s = new String(array);
            if (memo.containsKey(s)) {
                memo.get(s).add(str);
            } else {
                List<String> values = new ArrayList<>();
                values.add(str);
                memo.put(s, values);
            }
        }

        res.addAll(memo.values());
        return res;
    }

    static int longestConsecutive(int[] nums) {
        int res = 0;
        if (nums == null || nums.length == 0) {
            return res;
        }
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }
        for (Integer i : set) {
            // 非最开始元素
            if (set.contains(i - 1)) {
                continue;
            }
            int next = i + 1;
            while (set.contains(next)) {
                next = next + 1;
            }
            res = Math.max(res, next - i);
        }
        return res;
    }

    static int[] twoSum(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return new int[]{-1, -1};
        }
        Map<Integer, Integer> memo = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (memo.containsKey(target - nums[i])) {
                return new int[]{memo.get(target - nums[i]), i};
            }
            memo.put(nums[i], i);
        }
        return new int[]{-1, -1};
    }

}
