package com.example.algorithm.leetcode;

import java.util.Arrays;

public class Code_48 {

    public static void main(String[] args) {
        int[][] matrix = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        rotate(matrix);
        System.out.println(Arrays.deepToString(matrix));
    }

    private static void rotate(int[][] matrix) {
        int length = matrix.length;
        int[][] result = new int[length][length];
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                result[j][length - i - 1] = matrix[i][j];
            }
        }
        for (int i = 0; i < length; i++) {
            System.arraycopy(result[i], 0, matrix[i], 0, length);
        }
    }
}
