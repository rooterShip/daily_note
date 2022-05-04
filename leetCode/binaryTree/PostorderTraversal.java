package binaryTree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

/*
 * @Author: rooterShip
 * @Date: 2022-05-04 19:03:34
 * @LastEditors: rooterShip
 * @LastEditTime: 2022-05-04 21:41:07
 */
// 后序遍历
public class PostorderTraversal {
    public static List<Integer> postorderTraversal(TreeNode root){
        List<Integer> res = new ArrayList<>(); //存放节点的value
        Stack<TreeNode> stack = new Stack<>(); //存放路径节点

        while(root!=null || !stack.isEmpty()){
            while(root!=null){
                res.add(root.val); //尽可能的向右遍历 直到不能再向右，并将路径上的节点值加入res
                stack.push(root);
                root = root.right;
            }
            root = stack.pop(); //当不能再向右延伸的时候，弹出一个元素尝试向左稍微挪一下继续尝试向右
            root = root.left;
        }
        Collections.reverse(res); //循环结束后的顺序为 根 右 左; 而后续遍历的顺序是 左 右 根。所以reverse一下即可。
        return res;
    }
    public static void main(String[] args) {
        
    }
}