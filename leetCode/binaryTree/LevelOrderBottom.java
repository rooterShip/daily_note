/*
 * @Author: rooterShip
 * @Date: 2022-05-10 22:56:50
 * @LastEditors: rooterShip
 * @LastEditTime: 2022-05-12 19:17:55
 */
/**
 * 107 二叉树的层序遍历 II
 * 给你二叉树的根节点 root ，返回其节点值 自底向上的层序遍历 。 
 * （即按从叶子节点所在层到根节点所在的层，逐层从左向右遍历
 * 
 * 输入：root = [3,9,20,null,null,15,7]
 * 输出：[[15,7],[9,20],[3]]
 * 
 * 输入：root = [1]
 * 输出：[[1]]
 * 
 * 输入：root = []
 * 输出：[]
 */
package binaryTree;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.List;

public class LevelOrderBottom {
    public static List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) // 特判，若为空直接返回结果队列
            return res;
        Deque<TreeNode> deque = new ArrayDeque<>(); // 跑BFS时用来储存节点值
        deque.add(root); // 将根节点压入队列

        while (!deque.isEmpty()) {
            int cnt = deque.size(); // 上一层节点的个数
            List<Integer> level = new ArrayList<>(); // 储存每一层的节点值
            while (cnt != 0) { // 遍历每一层的节点
                TreeNode cur = deque.poll(); // 弹出当前节点
                level.add(cur.val); // 将当前节点的val值压入list中
                if (cur.left != null) // 左子树不为空压入队列
                    deque.add(cur.left);
                if (cur.right != null) // 右子树不为空压入队列
                    deque.add(cur.right);
                cnt--;
            }
            res.add(level); // 将每一层的list压入res队列
        }
        Collections.reverse(res); // 根据题意将队列翻转
        return res;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        TreeNode A = new TreeNode(9);
        TreeNode B = new TreeNode(20);
        TreeNode C = new TreeNode(15);
        TreeNode D = new TreeNode(7);
        root.left = A;
        root.right = B;
        B.left = C;
        B.right = D;
        System.out.println(levelOrderBottom(root).toString());
    }
}
