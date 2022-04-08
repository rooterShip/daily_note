import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/*
 * @Author: Rooter
 * @Date: 2022-04-08 22:35:40
 * @LastEditors: Rooter
 * @LastEditTime: 2022-04-08 23:07:13
 */
/**
 * 给定一个 N 叉树，返回其节点值的层序遍历。（即从左到右，逐层遍历）。
 * 树的序列化输入是用层序遍历，每组子节点都由 null 值分隔（参见示例）。
 * 
 * 输入：root = [1,null,3,2,4,null,5,6]
 * 输出：[[1],[3,2,4],[5,6]]
 * 
 * 输入：root = [1,null,2,3,4,5,null,null,6,7,null,8,null,9,10,null,null,11,null,12,null,13,null,null,14]
 * 输出：[[1],[2,3,4,5],[6,7,8,9,10],[11,12,13],[14]]
 */
public class LevelOrder {
    public static List<List<Integer>> levelOrder(Node root) {
        if(root == null){
            return new ArrayList<List<Integer>>();
        }

        List<List<Integer>> ans = new ArrayList<>();
        Queue<Node> queue = new ArrayDeque<>();
        queue.offer(root);

        while(!queue.isEmpty()){
            int cnt = queue.size(); //表示上一层的节点个数
            List<Integer> level = new ArrayList<>();
            for(int i = 0; i < cnt; i++){
                Node cur = queue.poll();
                level.add(cur.val);
                for(Node child : cur.children){
                    queue.offer(child);
                }
            }
            ans.add(level);
        }
        return ans;
    }
    public static void main(String[] args) {
        
    }
}

class Node {
    public int val;
    public List<Node> children;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, List<Node> _children) {
        val = _val;
        children = _children;
    }
}