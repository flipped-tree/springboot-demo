package com.example.springboot.practice.concurrency;

/**
 * 同步代码块的锁是任意对象。只要不同的线程都执行同一个同步代码块的时候，这个锁随便设。
 * <p>
 * 同步函数的锁是固定的 this。当需要和同步函数中的逻辑实行同步的时候，代码块中的锁必须为 this。
 * <p>
 * 静态同步函数的锁是该函数所属类的字节码文件对象。该对象可以用 this.getClass() 方法获取，也可以使用 当前类名.class 表示。
 *
 * @author xingce
 * @date 2019/12/20 10:48
 */
public class TraditionalThreadSynchronized {

    public static void main(String[] args) {
        new TraditionalThreadSynchronized().init();
    }

    private void init() {
        final OutPutter outPutter = new OutPutter();

        new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(5);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                outPutter.output("hello");
            }
        }).start();

        new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                outPutter.write("world");
            }
        }).start();

        new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                outPutter.out("java");
            }
        }).start();
    }

    static class OutPutter {

        private final String token = "";

        synchronized void output(String name) {
            synchronized (this) {
                print(name);
            }
        }

        synchronized void out(String name) {
            // 任何一个对象都可以作为参数，但是该对象对于两个线程来说是同一个才行
            // 如果用name就不行了，因为不同的线程进来name是不一样的，不是同一个name
            synchronized (token) {
                print(name);
            }
        }

        synchronized void write(String name) {
            print(name);
        }

        static synchronized void outPut(String name) {
            print(name);
        }
    }

    private static void print(String name) {
        int len = name.length();
        for (int i = 0; i < len; i++) {
            System.out.print(name.charAt(i));
        }
        System.out.println("");
    }

}
