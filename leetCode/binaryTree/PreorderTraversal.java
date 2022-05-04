package binaryTree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/*
 * @Author: rooterShip
 * @Date: 2022-05-04 18:46:33
 * @LastEditors: rooterShip
 * @LastEditTime: 2022-05-04 21:41:55
 */
// 二叉树的前序遍历
public class PreorderTraversal {
    public static List<Integer> preorderTraversals(TreeNode root){
        List<Integer> res = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();

        while(root!=null || !stack.isEmpty()){
            while(root!=null){  //尽可能的向左遍历 直到不能再向左，并将路径上的节点值加入res
                res.add(root.val);
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();//当不能再向左延伸的时候，弹出一个元素尝试向右稍微挪一下继续尝试向左
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