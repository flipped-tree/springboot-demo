package com.example.springboot.test;

import java.util.TreeMap;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author xingce
 * @date 2021/01/07 11:02
 */
public class DataHolder {

    static LinkedBlockingQueue<FileContent> queue = new LinkedBlockingQueue<>();

    static TreeMap<String, FileContent> treeMap = new TreeMap<>();

    public static LinkedBlockingQueue<FileContent> getQueue() {
        return queue;
    }

    public static TreeMap<String, FileContent> getTreeMap() {
        return treeMap;
    }
}
