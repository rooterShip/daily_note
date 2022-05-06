package binaryTree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/*
 * @Author: rooterShip
 * @Date: 2022-05-04 18:46:33
 * @LastEditors: rooterShip
 * @LastEditTime: 2022-05-06 22:57:54
 */
// 二叉树的前序遍历
public class PreorderTraversal {
    public static List<Integer> preorderTraversals(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();

        while (root != null || !stack.isEmpty()) {
            while (root != null) { // 尽可能的向左遍历 直到不能再向左，并将路径上的节点值加入res
                res.add(root.val);
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();// 当不能再向左延伸的时候，弹出一个元素尝试向右稍微挪一下继续尝试向左
            root = root.right;
        }

        return res;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        TreeNode A = new TreeNode(2);
        TreeNode B = new TreeNode(3);
        TreeNode C = new TreeNode(4);
        TreeNode D = new TreeNode(5);
        TreeNode E = new TreeNode(6);
        TreeNode F = new TreeNode(7);
        root.left = A;
        root.right = B;
        A.left = C;
        A.right = D;
        B.left = E;
        B.right = F;
        System.out.println(preorderTraversals(root).toString());
    }
}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {
    };

    TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}