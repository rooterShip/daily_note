/*
 * @Author: rooterShip
 * @Date: 2022-05-19 21:50:27
 * @LastEditors: rooterShip
 * @LastEditTime: 2022-05-19 22:23:53
 */
/**
 * 199. 二叉树的右视图
 * 给定一个二叉树的 根节点 root，想象自己站在它的右侧，按照从顶部到底部的顺序，返回从右侧所能看到的节点值。
 * 
 * 输入: [1,2,3,null,5,null,4]
 * 输出: [1,3,4]
 * 
 * 输入: [1,null,3]
 * 输出: [1,3]
 * 
 * 输入: []
 * 输出: []
 */
package binaryTree;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class RightSideView {
    public static List<Integer> rightSideView(TreeNode root) {
        List<Integer> res = new ArrayList<>(); // 结果列表
        if (root == null)
            return res; // 特判

        Deque<TreeNode> deque = new ArrayDeque<>(); // 储存节点
        deque.add(root); // 将根节点压进去

        while (!deque.isEmpty()) {
            int levelSize = deque.size(); // 上一层节点的个数
            List<Integer> level = new ArrayList<>(); // 储存每一层的节点的val值
            while (levelSize != 0) { // 以层为单位遍历每一个节点
                TreeNode curNode = deque.poll(); // 弹出当前节点
                level.add(curNode.val); // 将当前节点的val值压入层列表中
                if (curNode.left != null) // 广度遍历 左子树不为null 将左子树压入结果队列（此时该节点应该属于下一个节点）
                    deque.add(curNode.left);
                if (curNode.right != null) // 广度遍历 右子树不为null 将右子树压入结果队列（此时该节点应该属于下一个节点）
                    deque.add(curNode.right);
                levelSize--; // 每遍历完一个节点，该层所剩的节点数减一 等于0的时候跳出循环
            }
            res.add(level.get(level.size() - 1)); // 取层列表最后一个元素=>该层的最后一个节点（从右边视角看到的第一个节点）
        }
        return res;
    }

    public static void main(String[] args) {
        // 建立树的测试用例的时候要从下往上 要不然会提前用到还没定义的节点
        TreeNode C = new TreeNode(5);
        TreeNode D = new TreeNode(4);
        TreeNode A = new TreeNode(2, null, C);
        TreeNode B = new TreeNode(3, null, D);
        TreeNode root = new TreeNode(1, A, B);
        System.out.println(rightSideView(root).toString());
    }
}
