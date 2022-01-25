package com.example.algorithm;

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
        node1.setNext(node2).setNext(node3).setNext(node4);

        printList(node1);

        System.out.println();

        ListNode node = reverse(node1);

        System.out.println();

        printList(node);

        ListNode newNode = reverseList(node);

        System.out.println();

        printList(newNode);

        System.out.println();
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

    private static ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode newHead = reverseList(head.next);
        head.next.next = head;
        // 作用：清除原本指向
        head.next = null;
        return newHead;
    }
}

class ListNode {
    ListNode next;
    int data;

    ListNode(int data) {
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
