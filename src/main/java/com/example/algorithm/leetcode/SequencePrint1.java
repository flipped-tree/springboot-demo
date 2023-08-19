package com.example.algorithm.leetcode;

/**
 * @author xingce
 * @date 2023/7/29 13:42
 */
public class SequencePrint1 {

    static class Print {
        int flag;
        int loopTime;

        public Print(int flag, int loopTime) {
            this.flag = flag;
            this.loopTime = loopTime;
        }

        private void print(String printStr, int currentFlag, int nextFlag) {
            for (int i = 0; i < loopTime; i++) {
                synchronized (this) {
                    while (flag != currentFlag) {
                        try {
                            this.wait();
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    System.out.print(printStr);
                    flag = nextFlag;
                    this.notifyAll();
                }
            }
        }
    }

    public static void main(String[] args) {
        Print print = new Print(1, 10);
        new Thread(() -> print.print("C", 3, 1)).start();
        new Thread(() -> print.print("A", 1, 2)).start();
        new Thread(() -> print.print("B", 2, 3)).start();
    }
}
