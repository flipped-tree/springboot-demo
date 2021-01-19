package com.example.springboot.practice.algorithm;

import java.util.HashMap;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author xingce
 * @date 2020-07-12 01:32
 */
public class LruCache {

    Node head;
    Node tail;
    HashMap<Integer, Node> map = new HashMap<>();
    int capacity;

    private static AtomicInteger integer = new AtomicInteger(0);

    public LruCache(int capacity) {
        this.capacity = capacity;
    }

    public int get(int key) {
        if (map.containsKey(key)) {
            Node node = map.get(key);
            delete(node);
            setHead(node);
            return node.value;
        }
        return -1;
    }

    /**
     * This method will delete node
     */
    private void delete(Node node) {
        if (node.prev != null) {
            node.prev.next = node.next;
        } else {
            head = node.next;
        }
        if (node.next != null) {
            node.next.prev = node.prev;
        } else {
            tail = node.prev;
        }
    }

    /**
     * This method will make passed node as head
     */
    private void setHead(Node node) {
        node.next = head;
        node.prev = null;

        if (head != null) {
            head.prev = node;
        }
        head = node;
        if (tail == null) {
            tail = head;
        }
    }

    public void set(int key, int value) {
        if (map.containsKey(key)) {
            Node old = map.get(key);
            old.value = value;
            delete(old);
            setHead(old);
        } else {
            Node newNode = new Node(key, value);
            if (map.size() >= capacity) {
                map.remove(tail.key);
                delete(tail);
                setHead(newNode);
            } else {
                setHead(newNode);
            }
            map.put(key, newNode);
        }
        integer.getAndIncrement();
        map.forEach((k, v) -> System.out.println("第" + integer.get() + "次执行," + k + "||" + v.toString()));
    }

    class Node {
        int key;
        int value;
        Node prev;
        Node next;

        public Node(int key, int value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "key=" + key +
                    ", value=" + value + "}";
        }
    }

    private void output() {
        map.forEach((k, v) -> {
            int prev = Objects.nonNull(v.prev) ? v.prev.key : -1;
            int next = Objects.nonNull(v.next) ? v.next.key : -1;
            System.out.println(k + "=========" + v.key + "=========" + v.value + "=========" + prev + "=========" + next);
        });
    }

    public static void main(String[] args) {
        LruCache lrucache = new LruCache(4);
        lrucache.set(1, 100);
        lrucache.set(10, 99);
        lrucache.set(15, 98);
        lrucache.set(10, 97);
        lrucache.set(12, 96);
        lrucache.set(18, 95);
        lrucache.set(1, 94);

        System.out.println(lrucache.get(1));
        System.out.println(lrucache.get(10));
        System.out.println(lrucache.get(15));

    }

}
