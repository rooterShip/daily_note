package binaryTree;

import java.util.ArrayDeque;
import java.util.Deque;

/*
 * @Author: Rooter
 * @Date: 2022-04-21 22:46:09
 * @LastEditors: rooterShip
 * @LastEditTime: 2022-05-21 21:36:30
 */
/**
 * 117 填充每个节点的下一个右侧节点指针 II
 * 给定一个二叉树
 * struct Node {
 * int val;
 * Node *left;
 * Node *right;
 * Node *next;
 * }
 * 填充它的每个 next 指针，让这个指针指向其下一个右侧节点。如果找不到下一个右侧节点，则将 next 指针设置为 NULL。
 * 初始状态下，所有 next 指针都被设置为 NULL。
 * 
 * 输入：root = [1,2,3,4,5,null,7]
 * 输出：[1,#,2,3,#,4,5,7,#]
 * 解释：给定二叉树如图 A 所示，你的函数应该填充它的每个 next 指针，以指向其下一个右侧节点，如图 B 所示。序列化输出按层序遍历顺序（由 next
 * 指针连接），'#' 表示每层的末尾。
 * 
 */
public class Connect {
    public static Node connect(Node root) {
        if (root == null) {
            return null;
        }

        Deque<Node> deque = new ArrayDeque<>();
        deque.add(root);
        while (!deque.isEmpty()) {
            int len = deque.size(); // 每层的数量
            Node pre = null; // 每一行第一个没有被指向的

            for (int i = 0; i < len; i++) {
                Node cur = deque.poll();
                if (pre != null) { // 如果前面不为空，说明不是第一个，那把前面的指向当前节点
                    pre.next = cur;
                }
                pre = cur;// 更新当前节点

                if (cur.left != null) {
                    deque.add(cur.left);
                }
                if (cur.right != null) {
                    deque.add(cur.right);
                }
            }
        }
        return root;
    }

    public static void main(String[] args) {

    }
}

class Node {
    public int val;
    public Node left;
    public Node right;
    public Node next;

    public Node() {
    };

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, Node _left, Node _right, Node _next) {
        val = _val;
        left = _left;
        right = _right;
        next = _next;
    }
}