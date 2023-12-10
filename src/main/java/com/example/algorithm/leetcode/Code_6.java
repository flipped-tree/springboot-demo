package com.example.algorithm.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * 思路：按顺序遍历将数字放到固定的位置
 */
public class Code_6 {
    public static void main(String[] args) {
        String str = "PAYPALISHIRING";
        System.out.println(convert(str, 3));
    }

    private static String convert(String str, int numRows) {
        if (str.isEmpty()) {
            return "";
        }
        List<StringBuilder> list = new ArrayList<>();
        for (int i1 = 0; i1 < numRows; i1++) {
            list.add(new StringBuilder());
        }
        int start = 0, position = -1;
        for (char c : str.toCharArray()) {
            list.get(start).append(c);
            if (start == 0 || start == numRows - 1) {
                position = -position;
            }
            start = start + position;
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (StringBuilder builder : list) {
            stringBuilder.append(builder.toString());
        }
        return stringBuilder.toString();
    }
}
