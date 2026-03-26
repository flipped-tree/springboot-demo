package com.example.algorithm;

import java.util.Currency;

/**
 * @author cexing
 * @date 2021/4/30 14:20
 */
public class ReverseNode {
    public static void main(String[] args) {
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        ListNode node5 = new ListNode(5);
        ListNode node6 = new ListNode(6);
        node1.setNext(node2).setNext(node3).setNext(node4).setNext(node5).setNext(node6);

//        System.out.println(getLength(node1));
//
//        printList(node1);
//
//        System.out.println();
//
//        ListNode node = reverse(node1);
//
//        System.out.println();
//
//        printList(node);
//
//        ListNode newNode = doubleReverse(node);
//        System.out.println();
//        printList(newNode);

//        ListNode listNode = reverseBetween(node1, 2, 4);
//        printList(listNode);
        ListNode result = swap(node1, 2);
        printList(result);
    }

    private static ListNode swap(ListNode head, int i) {
        if (head == null || i == 0) {
            return head;
        }
        int length = 0;
        ListNode tail = null;
        for (ListNode current = head; current != null; current = current.next) {
            length++;
            tail = current;
        }
        tail.next = head;
        int n = length - i % length;
        for (int j = 0; j < n - 1; j++) {
            head = head.next;
        }
        tail = head.next;
        head.next = null;
        return tail;
    }

    private static ListNode doubleReverse(ListNode head) {
        ListNode dummy = new ListNode(-1);
        dummy.setNext(head);
        ListNode current = dummy;
        while (current.next != null && current.next.next != null) {
            ListNode p1 = current.next;
            ListNode p2 = current.next.next;
            current.next = p2;
            p1.next = p2.next;
            p2.next = p1;
            current = p1;
        }
        return dummy.next;
    }

    static int getLength(ListNode head) {
        if (head == null) {
            return 0;
        }
        int length = 0;
        for (ListNode current = head; current != null; current = current.next) {
            length++;
        }
        return length;
    }

    private static void printList(ListNode head) {
        ListNode h = head;
        while (null != h) {
            System.out.print(h.getData() + "->");
            h = h.getNext();
        }
    }

    private static ListNode reverse(ListNode head) {
        ListNode pre = null;
        ListNode current = head;
        while (current != null) {
            ListNode temp = current.next;
            current.next = pre;
            pre = current;
            current = temp;
        }
        return pre;
    }

    static ListNode reverseBetween(ListNode head, int left, int right) {
        ListNode dummy = new ListNode(0, head);
        ListNode p0 = dummy;
        for (int i = 0; i < left - 1; i++) {
            p0 = p0.next;
        }

        ListNode pre = null;
        ListNode cur = p0.next;
        for (int i = 0; i < right - left + 1; i++) {
            ListNode nxt = cur.next;
            cur.next = pre; // 每次循环只修改一个 next，方便大家理解
            pre = cur;
            cur = nxt;
        }

        p0.next.next = cur;
        p0.next = pre;
        return dummy.next;
    }
}

class ListNode {
    ListNode next;
    int data;

    ListNode(int data) {
        this.data = data;
    }

    public ListNode(int data, ListNode next) {
        this.next = next;
        this.data = data;
    }

    public ListNode setNext(ListNode next) {
        this.next = next;
        return next;
    }

    public int getData() {
        return data;
    }

    public ListNode getNext() {
        return next;
    }
}
