package com.example.practice.basic;

/**
 * @author xingce14590
 * @date 2021/3/1 17:37
 */
public class App {
    public static void main(String[] args) {
        B b = new B();
        //我的输出结果是什么？
        b.scan();
    }

    static class A {
        public void scan() {
            doScan();
        }

        protected void doScan() {
            System.out.println("A.doScan");
        }
    }

    static class B extends A {
        @Override
        protected void doScan() {
            System.out.println("B.doScan");
        }
    }
}
