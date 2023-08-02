package com.example.algorithm.leetcode;

/**
 * @author xingce
 * @date 2023/8/2 16:19
 */
public class SequencePrint4 {
    public static void main(String[] args) {
        PrintBo printBo = new PrintBo(3);
        new Thread(() -> printBo.print("B", 1), "Thread_B").start();
        new Thread(() -> printBo.print("A", 0), "Thread_A").start();
        new Thread(() -> printBo.print("C", 2), "Thread_C").start();
    }

    static class PrintBo {

        int printTimes;

        int state;

        PrintBo(int printTimes) {
            this.printTimes = printTimes;
        }

        public void print(String letter, int mold) {
            for (int i = 0; i < printTimes; i++) {
                synchronized (this) {
                    while (state % 3 != mold) { // 使用while而不是if是由于被唤醒后，会从wait之后的代码开始执行
                        try {
                            this.wait();
                        } catch (InterruptedException e) {

                        }
                    }
                    state++;
                    System.out.print(letter);
                    this.notifyAll();
                }
            }
        }
    }
}
