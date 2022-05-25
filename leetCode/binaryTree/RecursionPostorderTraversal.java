
/*
 * @Author: rooterShip
 * 
 * @Date: 2022-05-25 21:26:23
 * 
 * @LastEditors: rooterShip
 * 
 * @LastEditTime: 2022-05-25 21:51:00
 */
/**
 * 145. 二叉树的后序遍历(递归版本)
 */
package binaryTree;

import java.util.ArrayList;
import java.util.List;

public class RecursionPostorderTraversal {
    public static List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        postorder(root, res);

        return res;
    }

    public static void postorder(TreeNode root, List<Integer> list) {
        if (root == null) { // 特判&&递归退出机制
            return;
        }

        postorder(root.left, list);
        postorder(root.right, list);
        list.add(root.val);
    }

    public static void main(String[] args) {
        TreeNode E = new TreeNode(6, null, null);
        TreeNode F = new TreeNode(7, null, null);
        TreeNode C = new TreeNode(4, null, null);
        TreeNode D = new TreeNode(5, null, null);
        TreeNode A = new TreeNode(2, C, D);
        TreeNode B = new TreeNode(3, E, F);
        TreeNode root = new TreeNode(1, A, B);
        System.out.println(postorderTraversal(root).toString());
    }
}