package com.example.algorithm.leetcode;

import java.util.concurrent.locks.LockSupport;

/**
 * @author xingce
 * @date 2023/8/2 16:54
 */
public class SequencePrint5 {

    static Thread a, b = null;

    public static void main(String[] args) {
        a = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                System.out.print(i);
                LockSupport.unpark(b);
                LockSupport.park();
            }
        });
        b = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                LockSupport.park(b);
                System.out.print((char)('A' + i));
                LockSupport.unpark(a);
            }
        });

        a.start();
        b.start();
    }
}
