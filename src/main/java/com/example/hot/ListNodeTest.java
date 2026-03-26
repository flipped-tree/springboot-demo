package com.example.hot;

import java.util.*;

public class ListNodeTest {

    public static void main(String[] args) {
        ListNode head1 = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5)))));
        ListNode head2 = new ListNode(7, new ListNode(6, new ListNode(3, new ListNode(4))));
        // 相交链表
        ListNode xiangjiao = xiangjiaoNode(head1, head2);
        System.out.println("xiang jiao node:" + xiangjiao.val);

        // 翻转链表
        ListNode reverseNode = reverseList(head1);
        System.out.println("reverseNode node:" + transfer(reverseNode));

        // 回文链表
        ListNode root = new ListNode(1, new ListNode(2, new ListNode(2, new ListNode(1))));
        boolean palindrome = isPalindrome(root);
        System.out.println("is palindrome:" + palindrome);

        // 环形链表
        ListNode n1 = new ListNode(1);
        ListNode n2 = new ListNode(2);
        ListNode n3 = new ListNode(3);
        n1.next = n2;
        n2.next = n3;
        n3.next = n2;
        boolean hasCycle = hasCycle(n1);
        System.out.println("has cycle:" + hasCycle);

        // 环形链表入口
        ListNode entrance = detectCycle(n1);
        System.out.println("cycle node entrance:" + entrance.val);
//        ListNode entrance1 = detectCycle1(n1);
//        System.out.println("cycle node entrance:" + entrance1.val);

        // 合并两个有序链表
        ListNode l1 = new ListNode(1, new ListNode(2, new ListNode(3)));
        ListNode l2 = new ListNode(2, new ListNode(3, new ListNode(4)));
        ListNode merge = mergeNode(l1, l2); // ListNode merge = merge(l1,l2);
        System.out.println("merge two sorted list" + transfer(merge));

        // 两数相加
        ListNode a1 = new ListNode(1, new ListNode(8));
        ListNode a2 = new ListNode(1, new ListNode(9));
        ListNode addNode = addNode(a1, a2);
        System.out.println("two node add:" + transfer(addNode));

        // 删除倒数第n个节点
        ListNode node = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5)))));
        ListNode remove = removeNthFromEnd(node, 2);
        System.out.println("delete n node from end:" + transfer(remove));
//        ListNode remove2 = removeNthFromEnd1(node, 2);
//        System.out.println("delete n node from end2:" + transfer(remove2));

        // 两两交换
        ListNode pairs = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5)))));
        ListNode swapPairs = swapPairs(pairs);
        System.out.println("swap pairs result:" + transfer(swapPairs));

        // k个一组翻转
        ListNode group = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5)))));
        ListNode groupNode = reverseKGroup(group, 2);
        System.out.println("reverse k group:" + transfer(groupNode));

        // 排序链表
        ListNode sortedNode = new ListNode(4, new ListNode(3, new ListNode(5, new ListNode(7, new ListNode(6)))));
        ListNode result = sortList(sortedNode);
        System.out.println("sorted node result:" + transfer(result));

        // 合并k个链表
        ListNode ll1 = new ListNode(1, new ListNode(3, new ListNode(6)));
        ListNode ll2 = new ListNode(2, new ListNode(3, new ListNode(9)));
        ListNode[] ln = new ListNode[]{ll1, ll2};
        ListNode res = mergeKLists(ln);
        System.out.println("merge k lists result:" + transfer(res));
    }

    static ListNode mergeKLists(ListNode[] lists) {
        PriorityQueue<ListNode> pq = new PriorityQueue<>((a, b) -> a.val - b.val);
        for (ListNode node : lists) {
            if (node != null) {
                pq.offer(node);
            }
        }
        ListNode dummy = new ListNode(-1);
        ListNode current = dummy;
        while (!pq.isEmpty()) {
            ListNode poll = pq.poll();
            if (poll.next != null) {
                pq.offer(poll.next);
            }
            current.next = poll;
            current = current.next;
        }
        return dummy.next;
    }

    static ListNode sortList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode middle = getMiddle(head);
        ListNode left = sortList(head);
        ListNode right = sortList(middle);
        return merge(left,right);
    }

    static ListNode reverseKGroup(ListNode head, int k) {
        int len = 0;
        for (ListNode cur = head; cur != null; cur = cur.next) {
            len++;
        }
        ListNode dummy = new ListNode(-1, head);
        ListNode p0 = dummy;
        ListNode pre = null;
        ListNode current = head;
        for (; len >= k; len = len - k) {
            for (int i = 0; i < k; i++) {
                ListNode next = current.next;
                current.next = pre;
                pre = current;
                current = next;
            }
            ListNode next = p0.next;
            p0.next.next = current;
            p0.next = pre;
            p0 = next;
        }
        return dummy.next;
    }

    private static ListNode swapPairs(ListNode root) {
        ListNode dummy = new ListNode(-1, root);
        ListNode pre = dummy;
        while (pre.next != null && pre.next.next != null) {
            ListNode first = pre.next;
            ListNode second = pre.next.next;
            pre.next = second;
            first.next = second.next;
            second.next = first;
            pre = first;
        }
        return dummy.next;
    }

    private static ListNode detectCycle1(ListNode root) {
        if (root == null) {
            return null;
        }
        ListNode cur = root;
        Set<ListNode> visited = new HashSet<>();
        while (cur != null) {
            if (visited.contains(cur)) {
                return cur;
            } else {
                cur = cur.next;
                visited.add(cur);
            }
        }
        return null;
    }

    private static ListNode removeNthFromEnd1(ListNode node, int n) {
        ListNode current = node;
        int size = 0;
        while (current != null) {
            current = current.next;
            size++;
        }
        ListNode dummy = new ListNode(-1, node);
        ListNode pre = dummy;
        for (int i = 0; i < size - n; i++) {
            pre = pre.next;
        }
        pre.next = pre.next.next;
        return dummy.next;
    }

    private static ListNode removeNthFromEnd(ListNode node, int n) {
        ListNode dummy = new ListNode(-1, node);
        ListNode left = dummy;
        ListNode right = dummy;
        while (n != 0) {
            right = right.next;
            n--;
        }
        while (right.next != null) {
            left = left.next;
            right = right.next;
        }
        left.next = left.next.next;
        return dummy.next;
    }

    static ListNode addNode(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(-1);
        ListNode current = dummy;
        int carry = 0;
        while (l1 != null || l2 != null) {
            int x = l1 != null ? l1.val : 0;
            int y = l2 != null ? l2.val : 0;
            int sum = x + y + carry;
            current.next = new ListNode(sum % 10);
            current = current.next;
            carry = sum / 10;
            if (l1 != null) {
                l1 = l1.next;
            }
            if (l2 != null) {
                l2 = l2.next;
            }
        }
        if (carry == 1) {
            current.next = new ListNode(carry);
        }
        return dummy.next;
    }

    private static ListNode mergeNode(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(-1);
        ListNode current = dummy;
        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                current.next = l1;
                l1 = l1.next;
            } else {
                current.next = l2;
                l2 = l2.next;
            }
            current = current.next;
        }
        current.next = l1 == null ? l2 : l1;
        return dummy.next;
    }

    private static ListNode merge(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        } else if (l2 == null) {
            return l1;
        }
        if (l1.val < l2.val) {
            l1.next = merge(l1.next, l2);
            return l1;
        } else {
            l2.next = merge(l1, l2.next);
            return l2;
        }
    }


    static ListNode detectCycle(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                while (slow != head) {
                    slow = slow.next;
                    head = head.next;
                }
                return slow;
            }
        }
        return null;
    }

    private static boolean hasCycle(ListNode root) {
        ListNode slow = root;
        ListNode fast = root;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                return true;
            }
        }
        return false;
    }

    static boolean isPalindrome(ListNode root) {
        if (root == null) {
            return false;
        }
        ListNode middle = getMiddle(root);
        ListNode swap = reverseList(middle);
        while (swap != null) {
            if (swap.val != root.val) {
                return false;
            }
            swap = swap.next;
            root = root.next;
        }
        return true;
    }

    private static ListNode getMiddle(ListNode root) {
        ListNode pre = root;
        ListNode slow = root;
        ListNode fast = root;
        while (fast != null && fast.next != null) {
            pre = slow;
            fast = fast.next.next;
            slow = slow.next;
        }
        pre.next = null;
        return slow;
    }

    static List<Integer> transfer(ListNode root) {
        List<Integer> res = new ArrayList<>();
        ListNode current = root;
        while (current != null) {
            res.add(current.val);
            current = current.next;
        }
        return res;
    }

    private static ListNode reverseList(ListNode root) {
        ListNode pre = null;
        ListNode current = root;
        while (current != null) {
            ListNode next = current.next;
            current.next = pre;
            pre = current;
            current = next;
        }
        return pre;
    }

    private static ListNode xiangjiaoNode(ListNode head1, ListNode head2) {
        if (head1 == null) {
            return null;
        }
        if (head2 == null) {
            return null;
        }
        ListNode pA = head1;
        ListNode pB = head2;
        while (pA.val != pB.val) {
            pA = pA == null ? head2 : pA.next;
            pB = pB == null ? head1 : pB.next;
        }
        return pA;
    }

    static class ListNode {
        int val;
        ListNode next;

        public ListNode() {
        }

        public ListNode(ListNode next) {
            this.next = next;
        }

        public ListNode(int val) {
            this.val = val;
        }

        public ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }
}
