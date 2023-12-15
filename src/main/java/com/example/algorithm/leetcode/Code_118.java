package com.example.algorithm.leetcode;

import java.util.ArrayList;
import java.util.List;

public class Code_118 {
    public static void main(String[] args) {
        List<List<Integer>> result = generate(5);
        System.out.println(result);
    }

    private static List<List<Integer>> generate(int numRows){
        List<List<Integer>> result = new ArrayList<>();
        for (int i = 0; i < numRows; i++) {
            List<Integer> row = new ArrayList<>();
            for (int j = 0; j <= i; j++) {
                if (j == 0 || j == i) {
                    row.add(1);
                } else {
                    row.add(result.get(i-1).get(j-1) + result.get(i-1).get(j));
                }
            }
            result.add(row);
        }
        return result;
    }
}
