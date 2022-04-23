
/*
 * @Author: Rooter
 * @Date: 2022-04-23 22:15:12
 * @LastEditors: Rooter
 * @LastEditTime: 2022-04-23 22:17:18
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