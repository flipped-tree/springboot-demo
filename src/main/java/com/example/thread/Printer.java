package com.example.thread;

public class Printer {

    public static void main(String[] args) {
        Print print = new Print();
        new Thread(() -> print.print("A", 1, 2)).start();
        new Thread(() -> print.print("B", 2, 3)).start();
        new Thread(() -> print.print("C", 3, 1)).start();
    }

    static class Print {
        int turn = 1;

        final Object lock = new Object();

        void print(String name, int myTurn, int nextTurn) {
            for (int i = 0; i < 10; i++) {
                synchronized (lock) {
                    while (turn != myTurn) {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                        }
                    }
                    System.out.print(name);

                    turn = nextTurn;

                    lock.notifyAll();
                }
            }

        }
    }
}
