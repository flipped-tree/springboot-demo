package com.example.springboot.practice.algorithm;

/**
 * @author xingce
 * @date 2020/6/29 23:05
 */
public class MyLinkedArray {

    private Node head;
    private Node last;
    private int size;

    public void insert(int data, int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }
        Node insertedNode = new Node(data);
        if (size == 0) {
            head = insertedNode;
            last = insertedNode;
        } else if (index == 0) {
            // 头节点
            insertedNode.next = head;
            head = insertedNode;
        } else if (index == size) {
            // 尾节点
            last.next = insertedNode;
            last = insertedNode;
        } else {
            Node prevNode = get(index - 1);
            insertedNode.next = prevNode.next;
            prevNode.next = insertedNode;
        }
        size++;
    }

    public Node get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        Node temp = head;
        for (int i = 0; i < index; i++) {
            temp = temp.next;
        }
        return temp;
    }

    public Node remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        Node removedNode;
        if (index == 0) {
            removedNode = head;
            head = head.next;
        } else if (index == size - 1) {
            Node prevNode = get(size - 1);
            removedNode = prevNode.next;
            prevNode.next = null;
            last = prevNode;
        } else {
            Node prevNode = get(index - 1);
            Node nextNode = prevNode.next.next;
            removedNode = prevNode.next;
            prevNode.next = nextNode;
        }
        size--;
        return removedNode;
    }

    public void output() {
        Node temp = head;
        while (temp != null) {
            System.out.println(temp.data);
            temp = temp.next;
        }
    }

    private static class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
        }
    }

    public static void main(String[] args) {
        MyLinkedArray myLinkedArray = new MyLinkedArray();
        myLinkedArray.insert(3,0);
        myLinkedArray.insert(7,1);
        myLinkedArray.insert(9,2);
        myLinkedArray.insert(1,3);
        myLinkedArray.insert(2,1);
        myLinkedArray.remove(0);
        myLinkedArray.output();
    }
}