package com.example.algorithm.leetcode.cache;

public class NodeList {

    Node head, tail;

    int capacity;

    int size;

    public NodeList(int capacity) {
        this.size = 0;
        this.capacity = capacity;
        head = new Node();
        tail = new Node();
        head.next = tail;
        tail.prev = head;
    }

    void removeNode(Node node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    void addToHead(Node node) {
        node.next = head.next;
        head.next.prev = node;
        head.next = node;
        node.prev = head;
    }

    void moveToHead(Node node) {
        removeNode(node);
        addToHead(node);
    }

    Node removeTail() {
        Node pre = tail.prev;
        removeNode(pre);
        return pre;
    }
}
