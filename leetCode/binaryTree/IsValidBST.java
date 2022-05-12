/*
 * @Author: rooterShip
 * @Date: 2022-05-12 19:25:40
 * @LastEditors: rooterShip
 * @LastEditTime: 2022-05-12 21:26:15
 */
/**
 * 98. 验证二叉搜索树
 * 给你一个二叉树的根节点 root ，判断其是否是一个有效的二叉搜索树。
 * 有效 二叉搜索树定义如下：
 * 节点的左子树只包含 小于 当前节点的数。
 * 节点的右子树只包含 大于 当前节点的数。
 * 所有左子树和右子树自身必须也是二叉搜索树。
 * 
 * 输入：root = [2,1,3]
 * 输出：true
 * 
 * 输入：root = [5,1,4,null,null,3,6]
 * 输出：false
 * 解释：根节点的值是 5 ，但是右子节点的值是 4 。
 */
package binaryTree;

// import java.util.ArrayList;
// import java.util.List;
import java.util.Stack;

public class IsValidBST {
    public static Boolean isValidBST(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>(); // 用来存放以访问节点（路径）
        // List<Integer> res = new ArrayList<>(); // 用来存放节点的val
        int flag = -Integer.MAX_VALUE; // 初始标志，判定下一个节点的值是否大于前一个节点的值时会用到

        while (root != null || !stack.isEmpty()) {
            while (root != null) { // 中序遍历
                stack.add(root);
                root = root.left;
            }
            root = stack.pop(); // 弹出不能再往下走的最后一个节点
            // res.add(root.val);
            if (root.val <= flag) // 判断该节点和上一个节点比是不是更大，如果变小了，返回false。
                return false;
            flag = root.val; // 更新flag的值
            root = root.right;
        }
        // for (int i = 0; i < res.size() - 1; i++) {
        // if (res.get(i) < res.get(i + 1))
        // continue;
        // else
        // return false;
        // }
        return true;
    }

    public static void main(String[] args) {
        // TreeNode root = new TreeNode(5);
        // TreeNode A = new TreeNode(1);
        // TreeNode B = new TreeNode(4);
        // TreeNode C = new TreeNode(3);
        // TreeNode D = new TreeNode(6);
        // root.left = A;
        // root.right = B;
        // B.left = C;
        // B.right = D;
        TreeNode root = new TreeNode(2);
        TreeNode A = new TreeNode(1);
        TreeNode B = new TreeNode(3);
        root.left = A;
        root.right = B;
        System.out.println(isValidBST(root).toString());
    }
}
