package com.example.algorithm;

import java.util.Arrays;

/**
 * 冒泡排序
 *
 * @author xingce
 * @date 2020/7/25 19:39
 */
public class BubbleSort {

    public static void main(String[] args) {
        int[] ints = {4, 3, 5, 6};
        sort(ints);
        System.out.println(Arrays.toString(ints));
    }

    private static void sort(int[] ints) {
        for (int i = 0; i < ints.length - 1; i++) {
            // 判断是否已交换，不交换即表示已排序结束
            boolean swapped = false;
            for (int j = 0; j < ints.length - 1 - i; j++) {
                if (ints[j] > ints[j + 1]) {
                    swap(ints, j);
                    swapped = true;
                }
            }
            if (!swapped) {
                break;
            }
        }
    }

    /**
     * 交换位置
     */
    private static void swap(int[] ints, int j) {
        int temp = ints[j + 1];
        ints[j + 1] = ints[j];
        ints[j] = temp;
    }
}
