package com.example.algorithm.leetcode;

public class Code_2 {
    public static void main(String[] args) {
        ListNode l1 = new ListNode(2);
        l1.next = new ListNode(4);
        l1.next.next = new ListNode(3);

        ListNode l2 = new ListNode(5);
        l2.next = new ListNode(6);
        l2.next.next = new ListNode(4);

        ListNode listNode = addTwoListNode(l1, l2, 0);
        listNode.printAllNodes();
    }

    private static ListNode addTwoListNode(ListNode l1, ListNode l2, int bit) {
        if (l1 == null && l2 == null) {
            return null;
        }
        int sum = bit;
        if (l1 != null) {
            sum += l1.val;
            l1 = l1.next;
        }
        if (l2 != null) {
            sum += l2.val;
            l2 = l2.next;
        }
        ListNode listNode = new ListNode(sum % 10);
        listNode.next = addTwoListNode(l1, l2, sum / 10);
        return listNode;
    }

    private static class ListNode {
        int val;
        ListNode next;

        ListNode(int val) {
            this.val = val;
        }

        void printAllNodes() {
            ListNode node = this;
            while (node != null) {
                System.out.print(node.val);
                node = node.next;
            }
        }
    }
}
