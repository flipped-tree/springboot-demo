package com.example.algorithm.qujian;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @author xingce
 * @date 2020/12/12 14:46
 */
public class Qujian {
    public static void main(String[] args) {
        int[][] s = {{6, 7}, {3, 4}, {7, 8}, {4, 5}};
        boolean isConflict = conflict(s);
        for (int[] t : s) {
            System.out.println(Arrays.toString(t));
        }
        System.out.println();
        int[][] result = merge(s);
        for (int[] t : result) {
            System.out.println(Arrays.toString(t));
        }
        System.out.println();
        int[][] r = insert(result, new int[]{2, 7});
        for (int[] t : r) {
            System.out.println(Arrays.toString(t));
        }
    }

    /**
     * 区间是否冲突
     */
    public static boolean conflict(int[][] s) {
        Arrays.sort(s, Comparator.comparingInt(v -> v[0]));
        for (int i = 1; i < s.length; i++) {
            if (s[i][0] < s[i - 1][1]) {
                return false;
            }
        }
        return true;
    }

    /**
     * 合并区间
     */
    public static int[][] merge(int[][] intervals) {
        // 先按照区间起始位置排序
        Arrays.sort(intervals, Comparator.comparingInt(v -> v[0]));
        // 遍历区间
        int[][] res = new int[intervals.length][2];
        int idx = -1;
        for (int[] interval : intervals) {
            // 如果结果数组是空的，或者当前区间的起始位置 > 结果数组中最后区间的终止位置，说明不重叠。
            // 则不合并，直接将当前区间加入结果数组。
            if (idx == -1 || interval[0] > res[idx][1]) {
                res[++idx] = interval;
            } else {
                // 反之说明重叠，则将当前区间合并至结果数组的最后区间
                res[idx][1] = Math.max(res[idx][1], interval[1]);
            }
        }
        return Arrays.copyOf(res, idx + 1);
    }

    public static int[][] insert(int[][] intervals, int[] newInterval) {
        int[][] res = new int[intervals.length + 1][2];
        int idx = 0;
        // 遍历区间列表：
        // 首先将新区间左边且相离的区间加入结果集
        int i = 0;
        while (i < intervals.length && intervals[i][1] < newInterval[0]) {
            res[idx++] = intervals[i++];
        }
        // 接着判断当前区间是否与新区间重叠，重叠的话就进行合并，直到遍历到当前区间在新区间的右边且相离，
        // 将最终合并后的新区间加入结果集
        while (i < intervals.length && intervals[i][0] <= newInterval[1]) {
            newInterval[0] = Math.min(intervals[i][0], newInterval[0]);
            newInterval[1] = Math.max(intervals[i][1], newInterval[1]);
            i++;
        }
        res[idx++] = newInterval;
        // 最后将新区间右边且相离的区间加入结果集
        while (i < intervals.length) {
            res[idx++] = intervals[i++];
        }

        return Arrays.copyOf(res, idx);
    }
}
