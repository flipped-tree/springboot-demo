package com.example.algorithm;

import java.util.ArrayList;
import java.util.List;

/**
 * 查询前k小个元素
 *
 * @author xingce
 */
public class QueryArrayTargetEdge {
    public static void main(String[] args) {
        int[] nums = {1, 3, 2, 2, 7, 8, 7};
        int k = 4;
        List<Integer> list = getLeastNumbers(nums, k);
        list.forEach(System.out::print);
    }

    private static List<Integer> getLeastNumbers(int[] nums, int k) {
        ArrayList<Integer> list = new ArrayList<>();
        int lens = nums.length;
        if (k > lens || k <= 0) {
            return list;
        }
        int start = 0;
        int end = lens - 1;
        int index = getPartition(nums, start, end);
        while (index != k - 1) {
            if (index > k - 1) {
                end = index - 1;
                index = getPartition(nums, start, end);
            } else {
                start = index + 1;
                index = getPartition(nums, start, end);
            }
        }
        for (int i = 0; i < k; i++) {
            list.add(nums[i]);
        }
        return list;
    }

    /**
     * {1, 3, 2, 2, 7, 8, 7}
     */
    private static int getPartition(int[] nums, int low, int high) {
        // 数组的第一个值作为中轴（分界点或关键数据）
        int tmp = nums[low];
        while (low < high) {
            while (low < high && nums[high] > tmp) {
                high--;
            }
            // 比中轴小的记录移到低端
            nums[low] = nums[high];
            while (low < high && nums[low] < tmp) {
                low++;
            }
            // 比中轴大的记录移到高端
            nums[high] = nums[low];
        }
        // 中轴记录到尾
        nums[low] = tmp;
        // 返回中轴的位置
        return low;
    }
}
