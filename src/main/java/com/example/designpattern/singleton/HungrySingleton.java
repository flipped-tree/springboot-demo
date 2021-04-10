package com.example.designpattern.singleton;

/**
 * @author xingce
 * @date 2021/03/26 22:42
 */
public class HungrySingleton {
    private static final HungrySingleton INSTANCE = new HungrySingleton();

    private HungrySingleton(){}

    public static HungrySingleton getInstance() {
        return INSTANCE;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            System.out.println(HungrySingleton.getInstance());
        }
    }
}
