
/*
 * @Author: rooterShip
 * 
 * @Date: 2022-05-23 22:24:46
 * 
 * @LastEditors: rooterShip
 * 
 * @LastEditTime: 2022-06-07 21:47:00
 */
/**
 * 95. 不同的二叉搜索树 II
 * 输入：n = 3
 * 输出：[[1,null,2,null,3],[1,null,3,2],[2,1,3],[3,1,null,null,2],[3,2,null,1]]
 * 
 * 输入：n = 1
 * 输出：[[1]]

 * 给你一个整数 n ，
 * 请你生成并返回所有由 n 个节点组成且节点值从 1 到 n 互不相同的不同 二叉搜索树 。可以按 任意顺序 返回答案。
 */
package binaryTree;

import java.util.ArrayList;
import java.util.List;

public class GenerateTrees {
    public static List<TreeNode> generateTrees(int n) {
        List<TreeNode> res = new ArrayList<>();
        return res;

    }

    public static TreeNode helper(int start, int end) {
        if (start > end)
            return null;

        int val = (start + end) / 2;// 折中为根节点（构造出的应该是平衡二叉树）
        TreeNode root = new TreeNode(val);
        root.left = helper(start, val - 1);
        root.right = helper(val + 1, end);
        return root;
    }

    public static void main(String[] args) {

    }
}