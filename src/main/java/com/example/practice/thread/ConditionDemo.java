package com.example.practice.thread;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author xingce
 * @date 2020/12/17 22:35
 */
public class ConditionDemo {

    public static void main(String[] args) {
        ConditionTest test = new ConditionTest();
        for (int i = 0; i < 9; i++) {
            int finalI = i;
            new Thread(() -> {
                test.push("发送消息" + finalI);
            }).start();
        }

        for (int i = 0; i < 3; i++) {
            new Thread(() -> {
                test.get();
            }).start();
        }
    }

}

class ConditionTest {
    ReentrantLock lock = new ReentrantLock();
    Condition start = lock.newCondition();
    Condition end = lock.newCondition();

    Queue<String> queue = new LinkedList<>();

    final int max_count = 3;

    public void push(String message) {
        lock.lock();
        try {
            while (queue.size() == max_count) {
                System.out.println("消息池已满");
                start.await();
            }
            System.out.println("消息已发送===>" + message);
            queue.offer(message);
            end.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void get() {
        lock.lock();
        try {
            while (queue.size() == 0) {
                System.out.println("消息池为空");
                end.await();
            }
            System.out.println("===消息已接收===>" + queue.poll());
            start.signal();
        } catch (Exception ex) {

        } finally {
            lock.unlock();
        }
    }
}
