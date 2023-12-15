package com.example.algorithm.leetcode;

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

    void printAllNodes() {
        ListNode current = this;
        while (current != null) {
            System.out.print(current.val);
            current = current.next;
        }
        System.out.println();
    }

    public int getVal() {
        return val;
    }

    public void setVal(int val) {
        this.val = val;
    }
}
