package com.example.algorithm.tree;

import java.util.*;

/**
 * @author xingce
 * @date 2020-09-02 22:37
 */
public class BinaryTree {

    private final TreeNode root;

    private TreeNode getRoot() {
        return root;
    }

    private static class TreeNode {
        int data;
        TreeNode left;
        TreeNode right;

        TreeNode(int data) {
            this.data = data;
        }

        public int getData() {
            return data;
        }

        public TreeNode getLeft() {
            return left;
        }

        public void setLeft(TreeNode left) {
            this.left = left;
        }

        public TreeNode getRight() {
            return right;
        }

        public void setRight(TreeNode right) {
            this.right = right;
        }
    }

    public BinaryTree(int[] array, int index) {
        root = createBinaryTree(array, index);
    }

    private static TreeNode createBinaryTree(int[] array, int index) {

        TreeNode treeNode = null;
        if (index < array.length) {
            treeNode = new TreeNode(array[index]);
            // 对于顺序存储的完全二叉树，如果某个节点的索引为index，其对应的左子树的索引为2*index+1，右子树为2*index+1
            treeNode.left = createBinaryTree(array, 2 * index + 1);
            treeNode.right = createBinaryTree(array, 2 * index + 2);
        }
        return treeNode;

    }

    public static void main(String[] args) {

        int[] datas = {1, 2, 3, 4, 5, 6};
        TreeNode root = createBinaryTree(datas, 0);
        // 前序遍历
        List<Integer> preOrder = preOrderTraversal(root);
        // 中序遍历
        List<Integer> inOrder = inOrderTraversal(root);
        // 后续遍历
        List<Integer> postOrder = postOrderTraversal(root);
        // 层序遍历
        List<List<Integer>> levelOrder = levelOrder(root);
        System.out.println("preOrder is : " + preOrder);
        System.out.println("inOrder is : " + inOrder);
        System.out.println("postOrder is : " + postOrder);
        System.out.println("levelOrder is : " + levelOrder);
    }

    /**
     * 镜像
     */
    private void mirror(TreeNode root) {
        if (root == null) {
            return;
        }
        TreeNode temp = root.right;
        root.right = root.left;
        root.left = temp;

        mirror(root.left);
        mirror(root.right);
    }

    /**
     * 层序遍历 从上往下
     * 从下往上 Collections.reverse 反转即可
     */
    public static List<List<Integer>> levelOrder(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }
        List<List<Integer>> res = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (queue.size() > 0) {
            int size = queue.size();
            ArrayList<Integer> tmp = new ArrayList<>();
            for (int i = 0; i < size; ++i) {
                TreeNode t = queue.poll();
                tmp.add(t.data);
                if (t.left != null) {
                    queue.offer(t.left);
                }
                if (t.right != null) {
                    queue.offer(t.right);
                }
            }
            res.add(tmp);
        }
        return res;
    }

    /**
     * 前序遍历 递归
     */
    private static List<Integer> preOrderRecursion(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        reverse(root, res);
        return res;
    }

    private static void reverse(TreeNode root, List<Integer> list) {
        if (root == null) {
            return;
        }
        list.add(root.data);
        reverse(root.left, list);
        reverse(root.right, list);
    }

    /**
     * 前序
     */
    private static List<Integer> preOrderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        Stack<TreeNode> stack = new Stack<>();
        TreeNode node = root;
        while (!stack.isEmpty() || node != null) {
            while (node != null) {
                result.add(node.data);
                stack.push(node);
                node = node.left;
            }
            node = stack.pop();
            node = node.right;
        }
        return result;
    }

    /**
     * 中序
     */
    private static List<Integer> inOrderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        while (stack.size() > 0 || root != null) {
            if (root != null) {
                stack.push(root);
                root = root.left;
            } else {
                TreeNode temp = stack.pop();
                list.add(temp.data);
                root = temp.right;
            }
        }
        return list;
    }

    /**
     * 后序
     */
    public static List<Integer> postOrderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode prev = null;
        while (stack.size() > 0 || root != null) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            if (root.right == null || root.right == prev) {
                result.add(root.data);
                prev = root;
                root = null;
            } else {
                stack.push(root);
                root = root.right;
            }
        }
        return result;
    }

    /**
     * 最大深度
     */
    private static int getMaxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        } else {
            int leftDepth = getMaxDepth(root.left);
            int rightDepth = getMaxDepth(root.right);
            return Math.max(leftDepth, rightDepth) + 1;
        }
    }

    /**
     * 最小深度
     */
    private static int getMinDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        if (root.left == null && root.right == null) {
            return 1;
        }
        int min = Integer.MAX_VALUE;
        if (root.left != null) {
            min = Math.min(getMinDepth(root.left), min);
        }
        if (root.right != null) {
            min = Math.min(getMinDepth(root.right), min);
        }
        return min + 1;
    }

    private static boolean isBalancedTree(TreeNode root) {
        if (root == null) {
            return false;
        } else {
            return Math.abs(getHeight(root.left) - getHeight(root.right)) <= 1 && isBalancedTree(root.left) && isBalancedTree(root.right);
        }
    }

    private static int getHeight(TreeNode root) {
        if (root == null) {
            return 0;
        } else {
            return Math.max(getHeight(root.left), getHeight(root.right)) + 1;
        }
    }

}
