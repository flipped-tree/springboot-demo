package com.example.practice.algorithm;

/**
 * @author xingce
 * @date 2020-06-28 10:02
 */
public class Solution {

    public static void main(String[] args) {
        MyLinkedList list = new MyLinkedList();
        list.addNode(1);
        list.addNode(2);
        list.addNode(3);
        list.printList();
        System.out.println(list.getSize());

        list.deleteNode(1);
        list.printList();
        System.out.println(list.getSize());
    }

}

class MyLinkedList {
    Node head;

    int size;

    class Node {
        int data;
        Node next;

        public Node(int data) {
            this.data = data;
        }
    }

    public void addNode(int data) {
        if (head == null) {
            head = new Node(data);
            size++;
            return;
        }

        Node temp = head;
        while (temp.next != null) {
            temp = temp.next;
        }
        temp.next = new Node(data);
        size++;
    }

    public Node deleteNode(int index) {
        //处理空链表
        if (size == 0) {
            return null;
        }
        //处理异常索引
        if (index < 1 || index > size) {
            return null;
        }
        //删除头结点
        if (index == 1) {
            Node temp = head;
            head = head.next;
            size--;
            return temp;
        }
        //删除非头结点
        //从头结点的下一个节点开始遍历
        Node cureNode = head.next;
        //记录当前循环的节点的上一个节点用于删除当前节点
        Node preNode = head;
        int i = 2;
        while (cureNode != null) {
            if (i == index) {
                //要删除的就是此节点
                preNode.next = cureNode.next;
                size--;
                break;
            } else {
                preNode = cureNode;
                cureNode = cureNode.next;
                i++;
            }
        }
        return cureNode;
    }

    public int getSize() {
        return size;
    }

    public void printList() {
        Node curNode = head;
        while (curNode != null) {
            System.out.print(curNode.data + " ");
            curNode = curNode.next;
        }
        System.out.println();
    }
}