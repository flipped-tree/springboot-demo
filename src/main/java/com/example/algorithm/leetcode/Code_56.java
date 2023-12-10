package com.example.algorithm.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Code_56 {
    public static void main(String[] args) {
        int[][] intervals = {{1, 3}, {2, 6}, {8, 10}, {15, 18}};
        System.out.println(Arrays.deepToString(merge(intervals)));
    }

    public static int[][] merge(int[][] a) {
        List<int[]> res = new ArrayList<>();
        Arrays.sort(a, Comparator.comparingInt(x -> x[0]));
        int l = a[0][0], r = a[0][1];
        for (int i = 1; i < a.length; i++) {
            if (a[i][0] > r) {
                res.add(new int[]{l, r});
                l = a[i][0];
                r = a[i][1];
            } else {
                r = Math.max(r, a[i][1]);
            }
        }
        return res.toArray(new int[0][]);
    }
}
