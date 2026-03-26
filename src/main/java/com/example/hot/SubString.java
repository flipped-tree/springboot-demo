package com.example.hot;

import java.util.*;

public class SubString {

    public static void main(String[] args) {
        // 和为k的子数组
        int[] nums = {1, 1, 1};
        int i = subarraySum(nums, 2);
        System.out.println(i);

        int i1 = subarraySum1(nums, 2);
        System.out.println(i1);

        int[] s = {1, 3, -1, -3, 5, 3, 6, 7};
        int[] ints = maxSlidingWindow(s, 3);
        System.out.println(Arrays.toString(ints));
    }

    static int[] maxSlidingWindow(int[] nums, int k) {
        int n = nums.length;
        // 窗口个数
        int[] ans = new int[n - k + 1];
        Deque<Integer> queue = new LinkedList<>();

        for (int right = 0; right < n; right++) {
            // 右边入
            while (!queue.isEmpty() && nums[queue.getLast()] <= nums[right]) {
                queue.removeLast();
            }
            queue.addLast(right);

            // 出 当队首元素的下标小于滑动窗口左侧边界left时
            int left = right - k + 1;
            if (queue.getFirst() < left) {
                queue.removeFirst();
            }

            if (left >= 0) {
                ans[left] = nums[queue.getFirst()];
            }
        }
        return ans;
    }

    static int subarraySum(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            int sum = 0;
            for (int j = i; j < nums.length; j++) {
                sum = sum + nums[j];
                if (sum == k) {
                    count++;
                }
            }
        }
        return count;
    }

    static int subarraySum1(int[] nums, int k) {
        int[] preSum = new int[nums.length + 1];
        for (int i = 0; i < nums.length; i++) {
            preSum[i + 1] = preSum[i] + nums[i];
        }
        // 转化为两数之和
        int res = 0;
        HashMap<Integer, Integer> preCnt = new HashMap<>();
        for (int sj : preSum) {
            int si = sj - k;
            System.out.println("si ====" + si);
            if (preCnt.containsKey(si)) { //已遍历元素中存在si
                res += preCnt.get(si);  //加上相应的个数
            }
            preCnt.put(sj, preCnt.getOrDefault(sj, 0) + 1); //当前遍历结束，更新sj出现个数，便于后续判断使用
        }
        return res;
    }

}
