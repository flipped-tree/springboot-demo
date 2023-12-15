package com.example.algorithm.leetcode.cache;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;

public class LFUCache {
    // 存储缓存的内容
    Map<Integer, Node> cache;
    // 春初每个频次对应的双向链表
    Map<Integer, LinkedHashSet<Node>> freqMap;
    int size;
    int capacity;
    // 存储当前最小频次
    int min;

    public LFUCache(int capacity) {
        this.cache = new HashMap<>();
        this.freqMap = new HashMap<>();
        this.capacity = capacity;
    }

    public int get(int key) {
        Node node = cache.get(key);
        if (node == null) {
            return -1;
        }
        reqInc(node);
        return node.val;
    }

    public void put(int key, int val) {
        if (capacity == 0) {
            return;
        }
        Node node = cache.get(key);
        if (node == null) {
            if (size == capacity) {
                Node deadNode = removeNode();
                cache.remove(deadNode.val);
                size--;
            }
            Node newNode = new Node(key, val);
            cache.put(key, newNode);
            addNode(newNode);
            size++;
        }
    }

    private void addNode(Node node) {
        LinkedHashSet<Node> set = freqMap.computeIfAbsent(1, k -> new LinkedHashSet<>());
        set.add(node);
        min = 1;
    }

    private Node removeNode() {
        LinkedHashSet<Node> set = freqMap.get(min);
        Node deadNode = set.iterator().next();
        set.remove(deadNode);
        return deadNode;
    }

    private void reqInc(Node node) {
        int freq = node.freq;
        LinkedHashSet<Node> set = freqMap.get(freq);
        set.remove(node);
        if (freq == min && set.isEmpty()) {
            min = freq + 1;
        }
        node.freq++;
        LinkedHashSet<Node> newSet = freqMap.computeIfAbsent(freq + 1, k -> new LinkedHashSet<>());
        newSet.add(node);
    }

}
