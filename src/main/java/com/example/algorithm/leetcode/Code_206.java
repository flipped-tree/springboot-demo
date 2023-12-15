package com.example.algorithm.leetcode;

public class Code_206 {
    public static void main(String[] args) {
        ListNode head = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5)))));
        ListNode node = iterateSwapNode(head);
        node.printAllNodes();

        ListNode listNode = recursionSwapNode(node);
        listNode.printAllNodes();
    }

    private static ListNode iterateSwapNode(ListNode head) {
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

    private static ListNode recursionSwapNode(ListNode head){
        if (head == null || head.next == null) {
            return head;
        }
        ListNode current = recursionSwapNode(head.next);
        head.next.next = head;
        head.next = null;
        return current;
    }
}
