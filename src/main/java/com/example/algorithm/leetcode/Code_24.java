package com.example.algorithm.leetcode;

public class Code_24 {
    public static void main(String[] args) {
        ListNode head = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4))));
        ListNode node = recursionSwapPairs(head);
        node.printAllNodes();

        ListNode listNode = iterateSwapPairs(node);
        listNode.printAllNodes();
    }

    private static ListNode recursionSwapPairs(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode first = head;
        ListNode second = first.next;
        ListNode third = second.next;

        second.next = first;
        first.next = recursionSwapPairs(third);
        return second;
    }


    private static ListNode iterateSwapPairs(ListNode head) {
        ListNode pre = new ListNode(0, head);
        ListNode current = pre;
        while (current.next != null && current.next.next != null) {
            ListNode first = current.next;
            ListNode second = current.next.next;
            current.next = second;
            first.next = second.next;
            second.next = first;
            current = first;
        }
        return pre.next;
    }
}
