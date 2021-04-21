package com.example.practice.algorithm;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * 手写字符串分割函数
 *
 * @author xingce
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
        while (str.contains(regx)) {
            limit = str.indexOf(regx);
            splitStr = str.substring(0, limit);
            str = str.substring(limit + regx.length());
            result.add(splitStr);
        }
        if (StringUtils.isNotBlank(str)) {
            result.add(str);
        }
        String[] strings = new String[result.size()];
        return result.toArray(strings);
    }
}
