
/*
 * @Author: rooterShip
 * 
 * @Date: 2022-05-25 21:53:42
 * 
 * @LastEditors: rooterShip
 * 
 * @LastEditTime: 2022-05-25 22:02:14
 */
/**
 * 94. 二叉树的中序遍历
 */
package binaryTree;

import java.util.ArrayList;
import java.util.List;

public class RecursionInorderTraversal {
    public static List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        inorder(root, res);
        return res;
    }

    public static void inorder(TreeNode root, List<Integer> list) {
        if (root == null) {
            return;
        }

        inorder(root.left, list); // 尽可能往左走，不能往左走的时候把当前节点压入结果队列。然后稍微往右挪一点继续向左
        list.add(root.val);
        inorder(root.right, list);
    }

    public static void main(String[] args) {
        TreeNode E = new TreeNode(6, null, null);
        TreeNode F = new TreeNode(7, null, null);
        TreeNode C = new TreeNode(4, null, null);
        TreeNode D = new TreeNode(5, null, null);
        TreeNode A = new TreeNode(2, C, D);
        TreeNode B = new TreeNode(3, E, F);
        TreeNode root = new TreeNode(1, A, B);
        System.out.println(inorderTraversal(root).toString());
    }
}