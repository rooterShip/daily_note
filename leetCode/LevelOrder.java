
/*
 * @Author: rooterShip
 * 
 * @Date: 2022-04-09 22:31:43
 * 
 * @LastEditors: rooterShip
 * 
 * @LastEditTime: 2022-05-09 22:36:53
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
    public static List<List<Integer>> levelOrder(MultiTreeNode root) {
        if (root == null) {
            return new ArrayList<List<Integer>>();
        }

        List<List<Integer>> ans = new ArrayList<>();
        Queue<MultiTreeNode> queue = new ArrayDeque<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int cnt = queue.size(); // 上一层的节点数
            List<Integer> level = new ArrayList<>();// 用来存放每一层的节点值
            for (int i = 0; i < cnt; i++) {
                MultiTreeNode cur = queue.poll(); // 遍历该层的每一个节点
                level.add(cur.val); // 每一层的节点值一个一个存进去
                for (int j = 0; j < cur.children.size(); j++) {
                    queue.offer(cur.children.get(j)); // 对该层的每一个节点的子节点放进队列，表示下一层的节点，进行下一层遍历
                }
            }
            ans.add(level); // level代表每一层的节点，把不同层的放进去就是整个树
        }
        return ans;
    }

    public static void main(String[] args) {
        // 多叉树初始化要为每一个节点都设置相应的子节点才不会空指针报错
        MultiTreeNode root = new MultiTreeNode(1);
        MultiTreeNode A = new MultiTreeNode(3);
        MultiTreeNode B = new MultiTreeNode(2);
        MultiTreeNode C = new MultiTreeNode(4);
        MultiTreeNode D = new MultiTreeNode(5);
        MultiTreeNode E = new MultiTreeNode(6);

        // root的children的初始化
        List<MultiTreeNode> list1 = new ArrayList<>();
        list1.add(A);
        list1.add(B);
        list1.add(C);
        root.children = list1;
        // A的children的初始化
        List<MultiTreeNode> list2 = new ArrayList<>();
        list2.add(D);
        list2.add(E);
        A.children = list2;
        // B、C的children的初始化
        List<MultiTreeNode> list3 = new ArrayList<>();
        List<MultiTreeNode> list4 = new ArrayList<>();
        B.children = list3;
        C.children = list4;
        // D、E的children的初始化
        List<MultiTreeNode> list5 = new ArrayList<>();
        List<MultiTreeNode> list6 = new ArrayList<>();
        D.children = list5;
        E.children = list6;

        System.out.println(levelOrder(root).toString());
    }
}

class MultiTreeNode {
    public int val;
    public List<MultiTreeNode> children;

    public MultiTreeNode() {
    }

    public MultiTreeNode(int _val) {
        val = _val;
    }

    public MultiTreeNode(int _val, List<MultiTreeNode> _children) {
        val = _val;
        children = _children;
    }
}
