/*
 * @Author: rooterShip
 * @Date: 2022-04-23 22:15:12
 * @LastEditors: rooterShip
 * @LastEditTime: 2022-04-25 19:17:51
 */
public class IsSubtree {
    public static boolean isSubtree(TreeNode root, TreeNode subRoot){
        return true;
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