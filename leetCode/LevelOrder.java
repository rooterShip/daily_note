
/*
 * @Author: rooterShip
 * 
 * @Date: 2022-04-09 22:31:43
 * 
 * @LastEditors: rooterShip
 * 
 * @LastEditTime: 2022-05-07 23:11:49
 */
import java.util.ArrayDeque;
import java.util.ArrayList;
// import java.util.Arrays;
import java.util.List;
import java.util.Queue;

/**
 * 429 N叉树的层序遍历
 * 
 * 给定一个 N 叉树，返回其节点值的层序遍历。（即从左到右，逐层遍历）。
 * 树的序列化输入是用层序遍历，每组子节点都由 null 值分隔（参见示例）。
 *
 * 输入：root = [1,null,3,2,4,null,5,6]
 * 输出：[[1],[3,2,4],[5,6]]
 *
 * 输入：root =
 * [1,null,2,3,4,5,null,null,6,7,null,8,null,9,10,null,null,11,null,12,null,13,null,null,14]
 * 输出：[[1],[2,3,4,5],[6,7,8,9,10],[11,12,13],[14]]
 */
public class LevelOrder {
    public static List<List<Integer>> levelOrder(Node root) {
        if (root == null) {
            return new ArrayList<List<Integer>>();
        }

        List<List<Integer>> ans = new ArrayList<>();
        Queue<Node> queue = new ArrayDeque<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int cnt = queue.size(); // 上一层的节点数
            List<Integer> level = new ArrayList<>();// 用来存放每一层的节点值
            for (int i = 0; i < cnt; i++) {
                Node cur = queue.poll(); // 遍历该层的每一个节点
                level.add(cur.val); // 每一层的节点值一个一个存进去
                for (Node child : cur.children) {
                    queue.offer(child); // 对该层的每一个节点的子节点放进队列，表示下一层的节点，进行下一层遍历
                }
            }
            ans.add(level); // level代表每一层的节点，把不同层的放进去就是整个树
        }
        return ans;
    }

    public static void main(String[] args) {
        // TODO:补充测试用例
        Node root = new Node(1);
        Node A = new Node(3);
        Node B = new Node(2);
        Node C = new Node(4);
        Node D = new Node(5);
        Node E = new Node(6);
        // FIXME:java.lang.NoSuchFieldError: children
        List<Node> list1 = new ArrayList<>();
        list1.add(A);
        list1.add(B);
        list1.add(C);
        List<Node> list2 = new ArrayList<>();
        list2.add(D);
        list2.add(E);

        // root.children = new ArrayList<>(Arrays.asList(A, B, C));
        // A.children = new ArrayList<>(Arrays.asList(D, E));
        System.out.println(levelOrder(root).toString());
    }
}
//FIXME:The type Node is already defined
class Node {
    public int val;
    public List<Node> children;

    public Node() {
    }

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, List<Node> _children) {
        val = _val;
        children = _children;
    }
}
