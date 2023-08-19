package com.example.algorithm.leetcode;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author xingce
 * @date 2023/8/2 15:27
 */
public class SequencePrint3 {

    public static class PrintBo {
        private final Lock lock = new ReentrantLock();
        int printTimes;
        int state;

        PrintBo(int printTimes) {
            this.printTimes = printTimes;
        }

        public void print(String letter, int mold) {
            for (int i = 0; i < printTimes;) {
                lock.lock();
                while (state % 3 == mold) {
                    state++;
                    i++;
                    System.out.print(letter);
                }
                lock.unlock();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        PrintBo printBo = new PrintBo(2);
        new Thread(() -> printBo.print("C", 2)).start();
        new Thread(() -> printBo.print("B", 1)).start();
        new Thread(() -> printBo.print("A", 0)).start();
    }
}
