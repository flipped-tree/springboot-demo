package com.example.practice.algorithm;

import jodd.util.StringUtil;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * 手写字符串分割函数
 *
 * @author xingce14590
 * @date 2021/3/22 19:46
 */
public class StringSplit {
    public static void main(String[] args) {
        String[] strings = splitByIndex("abcdefabsfsefsdfab", "ab");
        System.out.println(Arrays.toString(strings));
    }

    public static String[] splitByIndex(String str, String regx) {
        int limit;
        String splitStr;
        ArrayList<String> result = new ArrayList<>();
        while (str.contains(regx)) {// 设置要截取的位置
            limit = str.indexOf(regx);
            splitStr = str.substring(0, limit);// 设置截取后的字符串
            str = str.substring(limit + regx.length());
            result.add(splitStr);
        }
        if (!StringUtil.isBlank(str)) {
            result.add(str);
        }
        String[] strings = new String[result.size()];
        return result.toArray(strings);
    }
}
