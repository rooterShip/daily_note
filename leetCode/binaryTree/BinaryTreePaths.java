
/*
 * @Author: rooterShip
 * 
 * @Date: 2022-05-18 10:12:58
 * 
 * @LastEditors: rooterShip
 * 
 * @LastEditTime: 2022-05-18 21:08:03
 */
/**
 * 257 二叉树的所有路径
 * 给你一个二叉树的根节点 root ，按 任意顺序 ，返回所有从根节点到叶子节点的路径。
 * 叶子节点 是指没有子节点的节点。
 * 
 * 输入：root = [1,2,3,null,5]
 * 输出：["1->2->5","1->3"]
 * 
 * 输入：root = [1]
 * 输出：["1"]
 */
package binaryTree;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class BinaryTreePaths {
    public static List<String> binaryTreePaths(TreeNode root) {
        List<String> paths = new ArrayList<>(); // 存放结果路径
        if (root == null)
            return paths; // 特判
        Deque<TreeNode> Nodedeque = new ArrayDeque<>(); // 用来存储节点
        Nodedeque.add(root); // 讲根节点压入队列
        Deque<String> pathdeque = new ArrayDeque<>(); // 存放遍历过程中各个路径的情况
        pathdeque.add(String.valueOf(root.val)); // 遍历路径第一个节点是根节点

        while (!Nodedeque.isEmpty()) {
            TreeNode cur = Nodedeque.poll(); // 弹出当前节点
            String path = pathdeque.poll(); // 弹出能遍历到该节点情况下（两者是相对应吻合的）的路径情况

            if (cur.left == null && cur.right == null) // 如果是叶子节点，则将路径压入结果路径列表中
                paths.add(path);
            else { // 如果没遍历到子节点
                if (cur.left != null) { // 左子节点不为空 则将该节点压入节点队列 && 路径加上当前节点并压入路径队列
                    Nodedeque.add((cur.left));
                    pathdeque.add(path + "->" + String.valueOf(cur.left.val));
                }
                if (cur.right != null) {// 右子节点不为空 则将该节点压入节点队列 && 路径加上当前节点并压入路径队列
                    Nodedeque.add((cur.right));
                    pathdeque.add(path + "->" + String.valueOf(cur.right.val));
                }
            }
        }
        return paths;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        TreeNode A = new TreeNode(2);
        TreeNode B = new TreeNode(3);
        TreeNode C = new TreeNode(5);
        root.left = A;
        root.right = B;
        A.right = C;
        System.out.println(binaryTreePaths(root).toString());
    }
}