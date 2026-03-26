package com.example.test;

public class PackageProblem {

    public static void main(String[] args) {
        // 1. 定义物品数据
        int[] weights = {2, 3, 4, 5};   // 物品重量数组
        int[] values = {3, 4, 5, 6};    // 物品价值数组
        int capacity = 8;               // 背包最大容量

        // 2. 调用方法，计算最大价值
        int maxValue = knapsack(weights, values, capacity);

        // 3. 输出结果
        System.out.println("背包能装的最大价值 = " + maxValue);
    }

    /**
     * 0-1背包动态规划核心方法
     *
     * @param weights  物品重量数组
     * @param values   物品价值数组
     * @param capacity 背包容量
     * @return 最大价值
     */
    private static int knapsack(int[] weights, int[] values, int capacity) {
        // 物品数量
        int n = weights.length;

        // ===================== 定义DP数组 =====================
        // dp[j]：容量为j的背包，能装的最大价值
        // 数组长度 = 容量+1（下标0对应容量0，下标capacity对应最大容量）
        int[] dp = new int[capacity + 1];

        // ===================== 核心循环 =====================
        // 外层循环：遍历每一个物品（逐个决定选不选）
        for (int i = 0; i < n; i++) {
            // 当前物品的重量和价值
            int w = weights[i];
            int v = values[i];

            // 内层循环：逆序遍历背包容量（关键！防止物品重复选）
            // 从最大容量 遍历到 当前物品重量（小于重量装不下，无需遍历）
            for (int j = capacity; j >= w; j--) {
                // ===================== 状态转移公式 =====================
                // dp[j] = max( 不选当前物品 , 选当前物品 )
                // 不选：dp[j]（原值）
                // 选：dp[j - w] + v（剩余容量的最大价值 + 当前物品价值）
                dp[j] = Math.max(dp[j], dp[j - w] + v);
            }
        }

        // ===================== 返回结果 =====================
        // dp[capacity]：容量为capacity的背包的最大价值（最终答案）
        return dp[capacity];
    }

}
