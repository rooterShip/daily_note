/*
 * @Author: rooterShip
 * @Date: 2022-05-07 22:13:02
 * @LastEditors: rooterShip
 * @LastEditTime: 2022-05-07 22:46:42
 */
/**
 * 102 二叉树的层序遍历
 * 给你二叉树的根节点 root ，返回其节点值的 层序遍历 。 （即逐层地，从左到右访问所有节点）。
 * 
 * 输入：root = [3,9,20,null,null,15,7]
 * 输出：[[3],[9,20],[15,7]]
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
import java.util.Deque;
import java.util.List;

public class Binary_tree_level_order {

    public static List<List<Integer>> levelOrder(TreeNode root) {

        List<List<Integer>> res = new ArrayList<>();
        if (root == null) { // 特判 如果传入的root为空直接返回空结果
            return res;
        }
        Deque<TreeNode> deque = new ArrayDeque<>(); // 用来存放节点

        deque.add(root); // 将根节点压入队列
        while (!deque.isEmpty()) { // 跑BFS，直到最后一个节点弹出队列（不再有扩展子节点的时候）
            int size = deque.size(); // 储存上一层的节点数
            List<Integer> level = new ArrayList<>(); // 储存每一层的节点val
            while (size != 0) { // 循环结束代表该层遍历完成
                TreeNode cur = deque.pop(); // 将当前节点弹出
                level.add(cur.val); // 将当前节点的val的值压入层list中
                if (cur.left != null) // 二叉树只用判断是否存在左右两个节点，如果存在，压入队列
                    deque.add(cur.left);
                if (cur.right != null)
                    deque.add(cur.right);
                size--;
            }
            res.add(level); // 循环结束代表一层节点全部压入结果队列中，外层循环结束后就是整棵树
        }

        return res;

    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        TreeNode B = new TreeNode(9);
        TreeNode C = new TreeNode(20);
        TreeNode D = new TreeNode(15);
        TreeNode E = new TreeNode(7);
        root.left = B;
        root.right = C;
        C.left = D;
        C.right = E;
        System.out.println(levelOrder(root).toString());
    }
}
