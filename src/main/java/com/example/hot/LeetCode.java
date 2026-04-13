package com.example.hot;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class LeetCode {

    public static void main(String[] args) {
        List<String> result = generateParenthesis(3);
        System.out.println(result);

        for (String s : result) {
            boolean valid = isValid(s);
            System.out.println("is valid: " + valid);
        }

        int[][] grid = {{1, 3, 1}, {1, 5, 1}, {4, 2, 1}};
        int i = minPathSum(grid);
        System.out.println("min path:" + i);

        int[] nums = {5, 4, -1, 7, 8};
        int res = maxSubArray(nums);
        System.out.println("max sub array:" + res);
    }

    static int maxSubArray(int[] nums) {
        int len = nums.length;
        int[] preSum = new int[len];
        preSum[0] = nums[0];
        for (int i = 1; i < len; i++) {
            if (preSum[i - 1] < 0) {
                preSum[i] = nums[i];
            } else {
                preSum[i] = preSum[i - 1] + nums[i];
            }
        }
        int max = Integer.MIN_VALUE;
        for (int sum : preSum) {
            max = Math.max(max, sum);
        }
        return max;
    }

    static int minPathSum(int[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;
        int[][] dp = new int[rows][cols];
        dp[0][0] = grid[0][0];
        for (int i = 1; i < rows; i++) {
            dp[0][i] = dp[0][i - 1] + grid[0][i];
        }
        for (int i = 1; i < cols; i++) {
            dp[i][0] = dp[i - 1][0] + grid[i][0];
        }
        for (int i = 1; i < rows; i++) {
            for (int j = 1; j < cols; j++) {
                dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + grid[i][j];
            }
        }
        return dp[rows - 1][cols - 1];
    }

    // 有效括号
    static boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        for (char c : s.toCharArray()) {
            if (c == '(') {
                stack.push(')');
            } else if (c == '{') {
                stack.push('}');
            } else if (c == '[') {
                stack.push(']');
            } else if (stack.isEmpty() || stack.pop() != c) {
                return false;
            }
        }
        return true;
    }

    // 生成括号
    static List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList<>();
        generate(res, 0, 0, n, "");
        return res;
    }

    static void generate(List<String> res, int left, int right, int n, String s) {
        if (s.length() == 2 * n) {
            res.add(s);
            return;
        }
        if (left < n) {
            generate(res, left + 1, right, n, s + "(");
        }
        if (right < left) {
            generate(res, left, right + 1, n, s + ")");
        }
    }


    int path(int m, int n) {
        int[][] dp = new int[m][n];
        for (int i = 1; i < m; i++) {
            dp[i][0] = 1;
        }
        for (int i = 0; i < n; i++) {
            dp[0][i] = 1;
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }
        return dp[m - 1][n - 1];
    }

}
