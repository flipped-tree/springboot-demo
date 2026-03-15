package com.example.algorithm.leetcode;

public class Code_19 {
    public static void main(String[] args) {
        ListNode head = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5)))));
        ListNode node = removeNode(head, 1);
        node.printAllNodes();
        boolean b = hasCycle(head);
        System.out.println(b);
    }

    static boolean hasCycle(ListNode head){
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                return true;
            }
        }
        return false;
    }

    private static ListNode removeNode(ListNode head, int n) {
        if (head == null || n == 0) {
            return null;
        }
        ListNode pre = new ListNode(0, head);
        ListNode fast = pre, slow = pre;
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
