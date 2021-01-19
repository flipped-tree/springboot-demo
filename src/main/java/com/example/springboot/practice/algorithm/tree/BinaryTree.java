package com.example.springboot.practice.algorithm.tree;

import java.util.*;

/**
 * @author xingce
 * @date 2020-09-02 22:37
 */
public class BinaryTree {

    private TreeNode root;

    public TreeNode getRoot() {
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

    private void buildBiTree(TreeNode node, int data) {
        //如果根结点是空,那么设置根结点,并且设置数据域
        if (root == null) {
            root = new TreeNode(data);
        } else {
            // 根结点不为空,那么判断数据是否小于当前结点的数据
            if (data < node.getData()) {
                //如果小于,判断当前结点是否有左叶子结点
                if (node.getLeft() == null) {
                    //左叶子结点为空,设置左叶子结点,并且设置数据
                    node.setLeft(new TreeNode(data));
                } else {
                    //左叶子结点不为空,递归调用构建二叉树的函数
                    buildBiTree(node.getLeft(), data);
                }
            } else {
                //如果大于或等于,判断当前结点是否存在右叶子结点
                if (node.getRight() == null) {
                    //右叶子结点为空,设置右叶子结点,并且设置数据域
                    node.setRight(new TreeNode(data));
                } else {
                    //右叶子几点不为空,递归调用构建二叉树的函数
                    buildBiTree(node.getRight(), data);
                }
            }
        }
    }

    public static BinaryTree createBiTree(int[] datas) {
        BinaryTree binaryTree = new BinaryTree();
        for (int data : datas) {
            binaryTree.buildBiTree(binaryTree.getRoot(), data);
        }
        return binaryTree;
    }

    public static void main(String[] args) {

        int[] datas = {72, 37, 29, 55, 51, 80};
        BinaryTree biTree = createBiTree(datas);
        // 前序遍历
        List<Integer> preOrder = preOrderTraversal(biTree.getRoot());
        // 中序遍历
        List<Integer> inOrder = inOrderTraversal(biTree.getRoot());
        // 后续遍历
        List<Integer> postOrder = postOrderTraversal(biTree.getRoot());
        // 层序遍历
        List<List<Integer>> levelOrder = levelOrder(biTree.getRoot());
        System.out.println(preOrder);
        System.out.println(postOrder);
        System.out.println(inOrder);
        System.out.println(levelOrder);
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

    private static List<Integer> preOrder(TreeNode root) {
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
