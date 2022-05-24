
/*
 * @Author: rooterShip
 * 
 * @Date: 2022-05-24 22:07:48
 * 
 * @LastEditors: rooterShip
 * 
 * @LastEditTime: 2022-05-24 22:32:07
 */
/**
 * 114.二叉树的前序遍历（递归版本）
 */
package binaryTree;

import java.util.ArrayList;
import java.util.List;

public class RecursionPreorderTraversal {
    public static List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        preorder(root, res);
        return res;
    }

    public static void preorder(TreeNode root, List<Integer> res) {
        if (root == null) // 特判&&递归退出出口
            return;
        res.add(root.val); // 将当前节点的val值压入节点
        /* 对于前序而言，要先尽可能的向左遍历，当不能向左的时候，
        通过出口退出，然后稍微往右挪一下，然后能向左再继续向左（下一句代码）*/
        preorder(root.left, res); 
        preorder(root.right, res);
    }

    public static void main(String[] args) {
        TreeNode E = new TreeNode(6, null, null);
        TreeNode F = new TreeNode(7, null, null);
        TreeNode C = new TreeNode(4, null, null);
        TreeNode D = new TreeNode(5, null, null);
        TreeNode A = new TreeNode(2, C, D);
        TreeNode B = new TreeNode(3, E, F);
        TreeNode root = new TreeNode(1, A, B);
        System.out.println(preorderTraversal(root).toString());
    }
}