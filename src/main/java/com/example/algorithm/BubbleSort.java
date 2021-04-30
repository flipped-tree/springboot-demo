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
        int[] s = {4, 3, 2, 1};
        sort(s);
    }

    private static void sort(int[] ints) {
        for (int i = 0, length = ints.length; i < length; i++) {
            for (int j = 0; j < length - i - 1; j++) {
                if (ints[j] > ints[j + 1]) {
                    int temp = ints[j + 1];
                    ints[j + 1] = ints[j];
                    ints[j] = temp;
                }
            }
            System.out.println(Arrays.toString(ints));
        }
    }
}
