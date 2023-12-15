package com.example.algorithm.leetcode;

public class Code_19 {
    public static void main(String[] args) {
        ListNode head = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5)))));
        ListNode node = removeNode(head, 0);
        node.printAllNodes();
    }

    private static ListNode removeNode(ListNode head, int n) {
        ListNode pre = new ListNode(0, head);
        ListNode fast = head, slow = pre;
        while (n != 0) {
            fast = fast.next;
            n--;
        }
        while (fast.next != null) {
            fast = fast.next;
            slow = slow.next;
        }
        slow.next = slow.next.next;
        return pre.next;
    }
}
