package com.example.algorithm.recursion;

/**
 * @author xingce
 * @date 2020/12/12 13:29
 */
public class HanNuoTowel {

    public static void main(String[] args) {
        move(3, "A", "B", "C");
    }

    private static void move(int n, String from, String to, String depend) {
        if (n <= 0) {
            System.out.println("number error");
        } else if (n == 1) {
            moveOne(from, to);
        } else {
            move(n - 1, from, depend, to);
            // 移动一个盘子到to
            moveOne(from, to);
            move(n - 1, depend, to, from);
        }
    }

    private static void moveOne(String from, String to) {
        System.out.println("移动一个盘子从" + from + "到" + to);
    }

}
