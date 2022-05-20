
/*
 * @Author: rooterShip
 * 
 * @Date: 2022-05-20 21:54:02
 * 
 * @LastEditors: rooterShip
 * 
 * @LastEditTime: 2022-05-20 22:21:44
 */
/**
 * 114. 二叉树展开为链表
 * 给你二叉树的根结点 root ，请你将它展开为一个单链表：
 * 展开后的单链表应该同样使用 TreeNode ，其中 right 子指针指向链表中下一个结点，而左子指针始终为 null 。
 * 展开后的单链表应该与二叉树 先序遍历 顺序相同。 
 * 
 * 输入：root = [1,2,5,3,4,null,6]
 * 输出：[1,null,2,null,3,null,4,null,5,null,6]
 * 
 * 输入：root = []
 * 输出：[]
 * 
 * 输入：root = [0]
 * 输出：[0]
 * 
 * 题解来源https://leetcode.cn/problems/flatten-binary-tree-to-linked-list/solution/by-lin-shen-shi-jian-lu-k-z5vv/
 */
package binaryTree;

public class Flatten {
    public static void flatten(TreeNode root) {
        while (root != null) {
            TreeNode flag = root.left;
            if (flag != null) {
                while (flag.right != null) // 找到二叉树左子树右链的最后一个节点
                    flag = flag.right;
                flag.right = root.right; // 将二叉树的整个右子树全部放到flag的右子树
                root.right = root.left; // 把二叉树的左子树整体移到右侧
                root.left = null; // 把二叉树左边置空
            }
            root = root.right; // 经历完一个循环后左子树为空了，将root移到下一个节点继续遍历
        }
    }

    public static void main(String[] args) {
        TreeNode C = new TreeNode(3);
        TreeNode D = new TreeNode(4);
        TreeNode E = new TreeNode(6);
        TreeNode A = new TreeNode(2, C, D);
        TreeNode B = new TreeNode(5, null, E);
        TreeNode root = new TreeNode(1, A, B);
        flatten(root);
    }
}