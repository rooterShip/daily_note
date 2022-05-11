/*
 * @Author: rooterShip
 * @Date: 2022-05-10 22:56:50
 * @LastEditors: rooterShip
 * @LastEditTime: 2022-05-11 22:48:38
 */
/**
 * 103 二叉树的锯齿形层序遍历
 * 给你二叉树的根节点 root ，返回其节点值的 锯齿形层序遍历 。
 * （即先从左往右，再从右往左进行下一层遍历，以此类推，层与层之间交替进行）。
 * 
 * 输入：root = [3,9,20,null,null,15,7]
 * 输出：[[3],[20,9],[15,7]]
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

public class ZigzagTraversal {
    public static List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null)
            return res;
        Deque<TreeNode> deque = new ArrayDeque<>();

        deque.add(root); // 将根节点压入队列
        int flag = 0; // 标记第几层的，如果是偶数层就反转level
        while (!deque.isEmpty()) {
            int cnt = deque.size(); // 上一层的节点数
            List<Integer> level = new ArrayList<>(); // 每一层的节点的val放在一个list中
            while (cnt != 0) { // 遍历上一层的每一个节点
                TreeNode cur = deque.poll(); // 弹出当前节点
                level.add(cur.val); // 将当前节点的val值放入list中
                if (cur.left != null) // 如果子节点不为null将其放入BFS队列中
                    deque.add(cur.left);
                if (cur.right != null)
                    deque.add(cur.right);
                cnt--;
            }
            flag++; // 经历过一层遍历后说明层数+1，根据层级的奇偶来判断要不要反转
            if (flag % 2 == 0) {
                Collections.reverse(level);
                res.add(level);
            } else
                res.add(level); // 遍历完一层后将该层的level放入总List中
        }
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
        System.out.println(zigzagLevelOrder(root).toString());
    }
}
