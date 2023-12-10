package com.example.algorithm.leetcode;

import java.util.ArrayList;
import java.util.List;

public class Code_22 {
    public static void main(String[] args) {
        List<String> result = generateParenthesis(3);
        System.out.println(result);
    }

    public static List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList<>();
        if (n <= 0) {
            return res;
        }
        dfs("", 0, 0, n, res);
        return res;
    }

    /**
     * @param str   str
     * @param left  左括号使用了多少个
     * @param right 右括号是以哦那个了多少个
     * @param n     n
     * @param res   结果
     */
    private static void dfs(String str, int left, int right, int n, List<String> res) {
        if (left == n && right == n) {
            res.add(str);
            return;
        }
        if (left < right) {
            return;
        }
        if (left < n) {
            dfs(str + "(", left + 1, right, n, res);
        }
        if (right < n) {
            dfs(str + ")", left, right + 1, n, res);
        }
    }
}
