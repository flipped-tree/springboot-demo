package com.example.designpattern.singleton;

/**
 * @author xingce
 * @date 2021/03/26 22:41
 */
public class Main {
    public static void main(String[] args) {
//        for (int i = 0; i < 10; i++) {
//            System.out.println(HungrySingleton.getInstance());
//        }
//        for (int i = 0; i < 10; i++) {
//            System.out.println(LazySingleton.getInstance());
//        }
//        for (int i = 0; i < 10; i++) {
//            System.out.println(SingletonEnum.INSTANCE.getInstance());
//        }
        for (int i = 0; i < 10; i++) {
            System.out.println(CasSingleton.getInstance());
        }
    }
}
