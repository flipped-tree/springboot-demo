package com.example.algorithm.leetcode.cache;

public class Node {
    public int key, val;
    public Node prev;
    public Node next;
    // 频次
    int freq = 1;

    public Node() {
    }

    public Node(int key, int val) {
        this.key = key;
        this.val = val;
    }
}
