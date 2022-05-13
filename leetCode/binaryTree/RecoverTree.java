
/*
 * @Author: rooterShip
 * @Date: 2022-05-13 18:55:26
 * @LastEditors: rooterShip
 * @LastEditTime: 2022-05-13 20:51:47
 */
/**
 * 99 恢复二叉搜索树
 * 给你二叉搜索树的根节点 root ，该树中的 恰好 两个节点的值被错误地交换。请在不改变其结构的情况下，恢复这棵树。
 * 
 * 输入：root = [1,3,null,null,2]
 * 输出：[3,1,null,null,2]
 * 解释：3 不能是 1 的左孩子，因为 3 > 1 。交换 1 和 3 使二叉搜索树有效。
 * 
 * 输入：root = [3,1,4,null,null,2]
 * 输出：[2,1,4,null,null,3]
 * 解释：2 不能在 3 的右子树中，因为 2 < 3 。交换 2 和 3 使二叉搜索树有效。
 */
package binaryTree;

import java.util.Stack;

public class RecoverTree {
    public static void recoverTree(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>(); // 存放访问路径上的节点
        TreeNode pre = new TreeNode(Integer.MIN_VALUE); // 当前节点的前一个节点，且初始化构造函数的时候代入最小值作为第一个节点判断的比较值
        TreeNode firNode = null; // 找到不符合BST（从左到右递增）的第一个节点
        TreeNode secNode = null; // 找到不符合BST的第二个节点

        while (root != null || !stack.isEmpty()) { // 中序遍历
            while (root != null) {
                stack.add(root);
                root = root.left;
            }
            root = stack.pop();
            if (firNode == null && root.val < pre.val) // 找到第一个节点（firnode==null，说明这是第一个节点）且不符合条件
                firNode = pre;
            if (firNode != null && root.val < pre.val) // 找到第二个节点（如果不止有一处不符合BST，随着遍历继续进行，这个if语句将重新被执行，secNode的值将被刷新
                secNode = root;
            pre = root; // 更新pre的值（将现在的节点变成前一个节点，并继续遍历）
            root = root.right;
        }
        int temp = firNode.val; // 将第一个节点和第二个节点的val值互换
        firNode.val = secNode.val;
        secNode.val = temp;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        TreeNode A = new TreeNode(3);
        TreeNode B = new TreeNode(2);
        root.left = A;
        A.right = B;
        System.out.println(root.toString());
    }
}