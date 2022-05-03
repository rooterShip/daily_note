package binaryTree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/*
 * @Author: rooterShip
 * @Date: 2022-04-30 22:31:46
 * @LastEditors: rooterShip
 * @LastEditTime: 2022-05-03 21:18:17
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
/**
 * 前中后序遍历模板 https://www.bilibili.com/video/BV15K4y1Y7Gz?spm_id_from=333.999.0.0
 */
public class InorderTraversal {
    public static List<Integer> inorderTraversal(TreeNode root){
        List<Integer> res = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();

        while(root!=null || !stack.isEmpty()){
            while(root!=null){  //尽可能的向左下方遍历，在路上把路径压入栈，找到不能再往左的时候就是最左，即结束循环
                stack.add(root); //然后弹出栈中最后一个元素就是最左边的
                root = root.left;
            }
            root = stack.pop();
            res.add(root.val);  //左边走不通尝试稍微往右挪一下再继续往左走，期间在弹栈的过程中顺道把“根”位置的元素加入结果队列，实现 左 根 右
            root = root.right;
        }
        return res;
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