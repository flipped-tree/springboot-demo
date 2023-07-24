package com.example.base;

import java.util.concurrent.Semaphore;

/**
 * @author xingce
 * @date 2023/7/6 10:20
 */
public class SequencePrint {

    static class Print {
        private int flag;
        private int loopNum;

        public Print(int flag, int loopNum) {
            this.flag = flag;
            this.loopNum = loopNum;
        }

        public void print(String str, int currentFlag, int nextFlag) {
            for (int i = 0; i < loopNum; i++) {
                synchronized (this) {
                    while (flag != currentFlag) {
                        try {
                            this.wait();
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    System.out.print(str);
                    flag = nextFlag;
                    this.notifyAll();
                }
            }
        }
    }

    private static final Semaphore SEMAPHORE_A = new Semaphore(1);
    private static final Semaphore SEMAPHORE_B = new Semaphore(0);

    public static void main(String[] args) {
        // 第一种方法
//        Print print = new Print(1, 50);
//        new Thread(() -> {
//            print.print("A", 1, 2);
//        }).start();
//        new Thread(() -> {
//            print.print("B", 2, 1);
//        }).start();
        new Thread(() -> {
            for (int i = 0; i < 50; i++) {
                try {
                    SEMAPHORE_A.acquire();
                    System.out.print("A");
                    SEMAPHORE_B.release();
                } catch (InterruptedException e) {
                }
            }
        }).start();
        new Thread(() -> {
            for (int i = 0; i < 50; i++) {
                try {
                    SEMAPHORE_B.acquire();
                    System.out.print("B");
                    SEMAPHORE_A.release();
                } catch (InterruptedException e) {

                }
            }
        }).start();
    }
}