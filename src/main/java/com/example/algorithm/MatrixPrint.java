package com.example.algorithm;

import java.util.ArrayList;
import java.util.List;

/**
 * 矩阵顺时针打印
 */
public class MatrixPrint {
    public static void main(String[] args) {
        int[][] matrix = {{1, 2, 3, 4}, {5, 6, 7, 8}};
        List<Integer> integers = printMatrix(matrix);
        System.out.println(integers);
    }

    private static List<Integer> printMatrix(int[][] array) {
        if (array == null || array.length == 0) {
            return new ArrayList<>();
        }
        List<Integer> resultList = new ArrayList<>();
        // [top][left]    [top][right]
        // [bottom][left] [bottom][right]
        int top = 0, left = 0, bottom = array.length - 1, right = array[0].length - 1;
        while (top <= bottom && left <= right) {
            // 从左往右
            for (int i = left; i <= right; i++) {
                int i1 = array[top][i];
                resultList.add(i1);
            }
            top++;
            // 从上往下
            for (int i = top; i <= bottom; i++) {
                int i1 = array[i][right];
                resultList.add(i1);
            }
            right--;
            // 从右往左
            for (int i = right; i >= left; i--) {
                int i1 = array[bottom][i];
                resultList.add(i1);
            }
            bottom--;
            // 从下往上
            for (int i = bottom; i >= top; i--) {
                int i1 = array[i][left];
                resultList.add(i1);
            }
            left++;
        }

        return resultList;
    }
}
