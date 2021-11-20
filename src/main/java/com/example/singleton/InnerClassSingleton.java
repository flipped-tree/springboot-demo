package com.example.singleton;

/**
 * @author xingce
 * @date 2021/03/26 22:50
 */
public class InnerClassSingleton {

    private static class SingletonHolder {
        private static final InnerClassSingleton INSTANCE = new InnerClassSingleton();
    }

    private InnerClassSingleton() {
    }

    public static InnerClassSingleton getInstance() {
        return SingletonHolder.INSTANCE;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            System.out.println(InnerClassSingleton.getInstance());
        }
    }
}
