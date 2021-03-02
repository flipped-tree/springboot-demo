package com.example.practice.algorithm;

/**
 * 单想链表获取倒数第n个节点
 *
 * @author xingce
 * @date 2021/3/2 21:18
 */
public class GetNode {

    public static void main(String[] args) {
        Node node1 = new Node(1, "张三", "小张");
        Node node2 = new Node(3, "李四", "小李");
        Node node3 = new Node(4, "王五", "小王");
        Node node4 = new Node(2, "赵六", "小赵");

        node1.setNext(node2).setNext(node3).setNext(node4);

        Node node = getIndexNode(node1, 2);
        assert node != null;
        System.out.println(node.toString());
    }

    public static int getLength(Node head) {
        //空链表
        if (head.next == null) {
            return 0;
        }

        int length = 0;
        Node temp = head.next;

        while (temp != null) {
            length++;
            temp = temp.next;
        }
        return length;
    }

    public static Node getIndexNode(Node head, int index) {
        if (head.next == null) {
            //链表为空
            return null;
        }
        //链表长度
        int size = getLength(head);

        if (size <= 0 || index > size) {
            return null;
        }
        //第一个数据
        Node temp = head.next;
        for (int i = 0; i < size - index; i++) {
            temp = temp.next;
        }
        return temp;
    }

}

class Node {
    /**
     * 数据域，可以封装成一个类
     */
    public int num;
    public String name;
    public String nickName;
    /**
     * 下一节点
     */
    public Node next;

    public Node(int num, String name, String nickName) {
        this.num = num;
        this.name = name;
        this.nickName = nickName;
    }

    public Node setNext(Node next) {
        this.next = next;
        return this.next;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return "num:" + this.num + " name:" + this.name + " nickName:" + this.nickName;
    }
}