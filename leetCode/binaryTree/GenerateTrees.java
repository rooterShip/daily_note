
/*
 * @Author: rooterShip
 * 
 * @Date: 2022-05-23 22:24:46
 * 
 * @LastEditors: rooterShip
 * 
 * @LastEditTime: 2022-06-08 21:45:59
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
 * 
 * 题解；https://leetcode.cn/problems/unique-binary-search-trees-ii/solution/cong-gou-jian-dan-ke-shu-dao-gou-jian-suo-you-shu-/
 */
package binaryTree;

import java.util.ArrayList;
import java.util.List;

public class GenerateTrees {
    public static List<TreeNode> generateTrees(int n) {
        List<TreeNode> res = new ArrayList<>();
        if (n < 1)
            return res;
        return helper(1, n);
    }

    public static List<TreeNode> helper(int start, int end) {
        List<TreeNode> list = new ArrayList<>();
        if (start > end) {
            list.add(null);
            return list;
        }

        for (int i = start; i <= end; i++) {
            List<TreeNode> left = helper(start, i - 1);
            List<TreeNode> right = helper(i + 1, end);

            for (TreeNode lef : left) {
                for (TreeNode rig : right) {
                    TreeNode root = new TreeNode(i);
                    root.left = lef;
                    root.right = rig;
                    list.add(root);
                }
            }
        }
        return list;
    }

    public static void main(String[] args) {

    }
}