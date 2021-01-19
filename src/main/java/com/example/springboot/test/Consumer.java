package com.example.springboot.test;

import java.util.TreeMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author xingce
 * @date 2021/01/07 11:08
 */
public class Consumer implements Runnable {

    private CountDownLatch latch;

    LinkedBlockingQueue<FileContent> queue = DataHolder.getQueue();
    static TreeMap<String, FileContent> treeMap = DataHolder.getTreeMap();

    public void setLatch(CountDownLatch latch) {
        this.latch = latch;
    }

    @Override
    public void run() {
        try {
            while (true) {
                if (!queue.isEmpty()) {
                    FileContent dataItem = queue.take();
                    //map为空
                    if (treeMap.get(dataItem.getGroupId()) == null) {
                        put(dataItem.getGroupId(), dataItem);
                    } else {
                        FileContent minContent = treeMap.get(dataItem.getGroupId());
                        if (minContent.getQuota() > dataItem.getQuota()) {
                            put(dataItem.getGroupId(), dataItem);
                        }
                    }
                } else {
                    latch.countDown();
                    break;
                }
            }
        } catch (InterruptedException e) {
            System.out.println("中断状态" + Thread.currentThread().isInterrupted());
        }
    }

    static synchronized void put(String groupId, FileContent content) {
        treeMap.put(groupId, content);
    }
}
