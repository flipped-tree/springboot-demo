package com.example.algorithm.leetcode;

public class Code_122 {
    public static void main(String[] args) {
        int[] prices = {1,2,3,4,5};
        int sum = maxProfit(prices);
        System.out.println(sum);
    }

    private static int maxProfit(int[] prices) {
        int sum = 0;
        for (int i = 1; i < prices.length; i++) {
            if (prices[i - 1] < prices[i]) {
                sum = sum + prices[i] - prices[i - 1];
            }
        }
        return sum;
    }
}
