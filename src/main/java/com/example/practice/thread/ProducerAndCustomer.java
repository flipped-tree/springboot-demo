package com.example.practice.thread;

import java.util.LinkedList;

/**
 * @author xingce
 * @date 2020/12/15 18:24
 */
public class ProducerAndCustomer {
    public static void main(String[] args) {
        Storage storage = new Storage();

        for (int i = 1; i < 6; i++) {
            int finalI = i;
            new Thread(() -> storage.produce("生产者:" + finalI)).start();
        }

        for (int i = 1; i < 4; i++) {
            int finalI = i;
            new Thread(() -> storage.consume("消费者:" + finalI)).start();
        }
    }
}

class Storage {
    private final LinkedList<Object> list = new LinkedList<>();

    public void produce(String producer) {
        synchronized (list) {
            // 如果仓库已满
            while (list.size() == 100) {
                System.out.println("仓库已满，【" + producer + "】： 暂时不能执行生产任务!");
                try {
                    // 由于条件不满足，生产阻塞
                    list.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            // 生产产品
            list.add(new Object());

            System.out.println("【" + producer + "】：生产了一个产品\t【现仓储量为】:" + list.size());

            list.notifyAll();
        }
    }

    public void consume(String consumer) {
        synchronized (list) {
            //如果仓库存储量不足
            while (list.size() == 0) {
                System.out.println("仓库已空，【" + consumer + "】： 暂时不能执行消费任务!");
                try {
                    // 由于条件不满足，消费阻塞
                    list.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            list.remove();
            System.out.println("【" + consumer + "】：消费了一个产品\t【现仓储量为】:" + list.size());
            list.notifyAll();
        }
    }
}
