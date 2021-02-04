package com.example.practice.designpattern;

public class Singleton {

    private Singleton() {
    }

    private static class SingletonContext{
        private static final Singleton INSTANCE = new Singleton();
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                System.out.println(SingletonContext.INSTANCE);
            }).start();
        }
    }

}
