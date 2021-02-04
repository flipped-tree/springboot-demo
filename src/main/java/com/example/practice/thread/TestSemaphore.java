package com.example.practice.thread;

import java.util.concurrent.Semaphore;

/**
 * 字符串转数字 如果不合规返回0
 */
public class TestSemaphore {
    public static void main(String[] args) throws InterruptedException {
        Semaphore semaphore = new Semaphore(1);

        semaphore.acquire();

        String string = "12";
        boolean isNegative = string.charAt(0) == '-';
        int result = 0;
        for (int i = 0; i < string.length(); i++) {
            char c = string.charAt(i);
            if (i == 0 && (c == '-' || c == '+')) {
                continue;
            }
            if (c < '0' || c > '9') {
                System.out.println("非法输入");
                return;
            }
            result = result * 10 + (c - '0');
        }
        System.out.println(isNegative ? -result : result);
    }
}
