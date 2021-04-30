package com.example.algorithm;

import java.util.Arrays;

/**
 * 快速排序
 * 时间复杂度：O(N(logN))
 * 空间复杂度：O(logN)
 */
public class QuickSort {

    public static void main(String[] args) {
        int[] s = {6, 5, 4, 9, 7, 8};
        quickSort(s, 0, s.length - 1);
        System.out.println(Arrays.toString(s));
    }


    public static int getMiddle(int[] list, int low, int high) {
        // 数组的第一个值作为中轴（分界点或关键数据）
        int tmp = list[low];
        while (low < high) {
            while (low < high && list[high] > tmp) {
                high--;
            }
            // 比中轴小的记录移到低端
            list[low] = list[high];
            while (low < high && list[low] < tmp) {
                low++;
            }
            // 比中轴大的记录移到高端
            list[high] = list[low];
        }
        // 中轴记录到尾
        list[low] = tmp;
        // 返回中轴的位置
        return low;
    }

    public static void quickSort(int[] list, int low, int high) {
        if (low < high) {
            // 将list数组一分为二
            int middle = getMiddle(list, low, high);
            // 对低字表进行递归排序
            quickSort(list, low, middle - 1);
            // 对高字表进行递归排序
            quickSort(list, middle + 1, high);
        }
    }



}
