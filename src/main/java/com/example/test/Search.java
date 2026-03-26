package com.example.test;

public class Search {

    public static void main(String[] args) {
        //输入: nums = [4,5,6,7,0,1,2], target = 0
        int[] array = {4, 5, 6, 7, 0, 1, 2};
        int target = 0;
        int search = search(array, target);
        int search1 = search(null, target);
        System.out.println(search);
        System.out.println(search1);
    }

    public static int search(int[] array, int target) {
        if (array == null || array.length == 0) {
            return -1;
        }

        int left = 0;
        int right = array.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2; // 防止溢出的写法

            if (array[mid] == target) {
                return mid;
            }

            if (array[left] <= array[mid]) {
                if (target >= array[left] && target < array[mid]) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            } else {
                if (target > array[mid] && target <= array[right]) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
        }
        return -1;
    }

}
