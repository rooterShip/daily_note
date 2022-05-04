package binaryTree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/*
 * @Author: rooterShip
 * @Date: 2022-05-04 18:46:33
 * @LastEditors: rooterShip
 * @LastEditTime: 2022-05-04 19:01:20
 */
// 二叉树的前序遍历
public class PreorderTraversal {
    public static List<Integer> preorderTraversals(TreeNode root){
        List<Integer> res = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();

        while(root!=null || !stack.isEmpty()){
            while(root!=null){
                res.add(root.val);
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
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
    TreeNode(){};
    TreeNode(int val){this .val = val;}
    TreeNode(int val, TreeNode left, TreeNode right){
        this.val = val;
        this.left = left;
        this.right = right;
    }
}