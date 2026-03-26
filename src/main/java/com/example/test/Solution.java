package com.example.test;

class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        return add(l1, l2, 0);
    }

    ListNode add(ListNode l1, ListNode l2, int carry) {
        if (l1 == null && l2 == null) {
            return null;
        }
        int sum = carry;
        if (l1 != null) {
            sum += l1.val;
            l1 = l1.next;
        }
        if (l2 != null) {
            sum += l2.val;
            l2 = l2.next;
        }
        ListNode node = new ListNode(sum / 10);
        node.next = add(l1, l2, sum % 10);
        return node;
    }

    public class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }
}