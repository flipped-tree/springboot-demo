package com.example.practice.concurrency;

/**
 * @author xingce
 * @date 2019/12/20 11:27
 */
public class TraditionalThreadCommunication {

    public static void main(String[] args) {
        Business business = new Business();
        // 开启一个子线程
        new Thread(() -> {
            for (int i = 1; i <= 50; i++) {
                business.sub(i);
            }
        }).start();
        // main方法即主线程
        for (int i = 1; i <= 50; i++) {
            business.main(i);
        }
    }

}

/**
 * 用到的共同数据（包括同步锁）或共同的若干个方法应该归在同一个类身上，这种设计正好体现了高类聚和程序的健壮性
 */
class Business {

    private boolean bShouldSub = true;

    public synchronized void sub(int i) {
        System.out.println("sub thread start");
        while (!bShouldSub) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        for (int j = 1; j <= 10; j++) {
            System.out.println("sub thread sequence of " + j + ",loop of " + i);
        }
        bShouldSub = false;
        this.notify();
    }

    public synchronized void main(int i) {
        System.out.println("main thread start");
        while (bShouldSub) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        for (int j = 1; j <= 5; j++) {
            System.out.println("main thread sequence of " + j + ",loop of " + i);
        }
        bShouldSub = true;
        this.notify();
    }
}
