package binaryTree;

import java.util.ArrayList;
import java.util.List;

/*
 * @Author: rooterShip
 * @Date: 2022-04-30 22:31:46
 * @LastEditors: rooterShip
 * @LastEditTime: 2022-04-30 22:44:09
 */
/**
 * 给定一个二叉树的根节点 root ，返回 它的 中序 遍历 。
 * 1
 *  2
 * 3
 * 输入：root = [1,null,2,3]
 * 输出：[1,3,2]
 * 
 * 输入：root = []
 * 输出：[]
 * 
 * 输入：root = [1]
 * 输出：[1]
 */
public class InorderTraversal {
    public static List<Integer> inorderTraversal(TreeNode root){
        List<Integer> ans = new ArrayList<>();
        return ans;
    }
    public static void main(String[] args) {
        
    }
}

class TreeNode{
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode() {}
    TreeNode(int val) { this.val = val; }
    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}