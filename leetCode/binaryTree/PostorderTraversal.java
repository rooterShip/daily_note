package binaryTree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

/*
 * @Author: rooterShip
 * @Date: 2022-05-04 19:03:34
 * @LastEditors: rooterShip
 * @LastEditTime: 2022-05-04 19:14:56
 */
// 后序遍历
public class PostorderTraversal {
    public static List<Integer> postorderTraversal(TreeNode root){
        List<Integer> res = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();

        while(root!=null || !stack.isEmpty()){
            while(root!=null){
                res.add(root.val);
                stack.push(root);
                root = root.right;
            }
            root = stack.pop();
            root = root.left;
        }
        Collections.reverse(res);
        return res;
    }
    public static void main(String[] args) {
        
    }
}