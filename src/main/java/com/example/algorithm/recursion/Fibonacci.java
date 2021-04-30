package com.example.algorithm.recursion;

/**
 * @author xingce
 * @date 2019/9/19 20:29
 */
public class Fibonacci {
    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            System.out.println("通过递归:" + getResultByRecursive(i));
            System.out.println("通过非递归:" + getResultByUnRecursive(i));
            System.out.println("通过非递归数组:" + getResultByUnRecursiveArray(i));
        }
    }

    private static int getResultByRecursive(int n) {
        if (n < 2) {
            return n;
        }
        return getResultByRecursive(n - 1) + getResultByRecursive(n - 2);
    }

    private static int getResultByUnRecursive(int n) {
        if (n == 0 || n == 1) {
            return n;
        }
        int first = 0;
        int second = 1;
        int third = 0;
        for (int i = 2; i <= n; i++) {
            third = first + second;
            first = second;
            second = third;
        }
        return third;
    }

    private static int getResultByUnRecursiveArray(int n) {
        if (n == 0 || n == 1) {
            return n;
        }
        int[] array = new int[n + 1];
        array[0] = 0;
        array[1] = 1;
        for (int i = 2; i <= n; i++) {
            array[i] = array[i - 1] + array[i - 2];
        }
        return array[n];
    }
}
