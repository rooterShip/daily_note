
/*
 * @Author: rooterShip
 * @Date: 2022-05-17 09:19:17
 * @LastEditors: rooterShip
 * @LastEditTime: 2022-05-17 09:34:13
 */
/**
 * 230. 二叉搜索树中第K小的元素
 * 给定一个二叉搜索树的根节点 root ，和一个整数 k ，请你设计一个算法查找其中第 k 个最小元素（从 1 开始计数）。
 * 
 * 输入：root = [3,1,4,null,2], k = 1
 * 输出：1
 * 
 * 输入：root = [5,3,6,2,4,null,null,1], k = 3
 * 输出：3
 */
package binaryTree;

import java.util.Stack;

public class KthSmallest {
    public static int kthSmallest(TreeNode root, int k) {
        Stack<TreeNode> stack = new Stack<>(); // 储存路径上的节点
        int cnt = 0; // BST中序遍历严格递增，所以只要判断中序遍历时遍历到第K个数即第K小

        while (root != null || !stack.isEmpty()) { // 中序遍历
            while (root != null) {
                stack.add(root);
                root = root.left;
            }
            root = stack.pop();
            cnt++; // 每遍历一个数字cnt加1
            if (cnt == k) {
                return root.val; // 如果遍历到第K个数字了直接返回当前节点的val
            } else
                root = root.right; // 如果没有则继续遍历
        }
        return 0;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        TreeNode A = new TreeNode(1);
        TreeNode B = new TreeNode(4);
        TreeNode C = new TreeNode(2);
        root.left = A;
        root.right = B;
        A.right = C;

        int k = 1;
        System.out.println(kthSmallest(root, k));
    }
}