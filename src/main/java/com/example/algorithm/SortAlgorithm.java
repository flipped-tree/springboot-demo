package com.example.algorithm;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 * @author xingce
 * @date 2021/11/11 10:21
 */
public class SortAlgorithm {

    /**
     * 循环次数
     */
    public static final int LOOP_COUNT = 100;

    public static void main(String[] args) {
        Set<Integer> randomInts = new HashSet<>(100);
        Random random = new Random();
        for (int i = 0; i < LOOP_COUNT; i++) {
            randomInts.add(random.nextInt(500));
        }
        int[] array = randomInts.stream().mapToInt(Integer::intValue).toArray();

        long start = System.nanoTime();
        // 耗时 235800
//        bubbleSort(array);
        // 耗时 46000
        quickSort(array, 0, array.length - 1);
        // 耗时 101800
//        selectSort(array);
        long end = System.nanoTime() - start;
        System.out.println("耗时：" + end + "\n" + Arrays.toString(array));
    }


    private static int getMiddle(int[] list, int low, int high) {
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

    /**
     * 快速排序
     * 不稳定排序
     * 时间复杂度 O(nlog2n)
     * 空间复杂度 O(log2n)
     */
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

    /**
     * 选择排序
     * 不稳定排序
     */
    private static void selectSort(int[] array) {
        for (int i = 0; i < array.length; i++) {
            int min = i;
            for (int j = i + 1; j < array.length; j++) {
                if (array[j] < array[min]) {
                    min = j;
                }
            }
            if (min != i) {
                swap(array, min, i);
            }
        }
    }

    /**
     * 冒泡排序
     * 稳定排序
     * 最好状态：初始状态是正序的，一次排序就完成，时间复杂度 O(N)
     * 最坏情况：初始状态是反序的，时间复杂度 O(N²)
     * 时间复杂度 O(N²)
     * 空间复杂度 O(1)
     */
    private static void bubbleSort(int[] ints) {
        for (int i = 0; i < ints.length - 1; i++) {
            // 判断是否已交换，不交换即表示已排序结束
            boolean swapped = false;
            for (int j = 0; j < ints.length - 1 - i; j++) {
                if (ints[j] > ints[j + 1]) {
                    swap(ints, j, j + 1);
                    swapped = true;
                }
            }
            if (!swapped) {
                break;
            }
        }
    }

    /**
     * 交换i和j
     */
    private static void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}
