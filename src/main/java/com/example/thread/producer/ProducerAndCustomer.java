package com.example.thread.producer;

import java.util.LinkedList;
import java.util.stream.IntStream;

/**
 * @author xingce
 * @date 2020/12/15 18:24
 */
public class ProducerAndCustomer {
    public static void main(String[] args) {
        Storage storage = new Storage();

        IntStream.range(1, 6).forEach(s -> new Thread(() -> storage.produce("生产者:" + s)).start());

        IntStream.range(1, 4).forEach(s -> new Thread(() -> storage.consume("消费者:" + s)).start());
    }

    static class Storage {
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
                while (list.isEmpty()) {
                    System.out.println("仓库已空，【" + consumer + "】： 暂时不能执行消费任务!");
                    try {
                        // 由于条件不满足，消费阻塞
                        list.wait();
                    } catch (InterruptedException e) {
                        
                    }
                }

                list.remove();
                System.out.println("【" + consumer + "】：消费了一个产品\t【现仓储量为】:" + list.size());
                list.notifyAll();
            }
        }
    }
}


