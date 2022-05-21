/*
 * @Author: rooterShip
 * @Date: 2022-05-21 21:37:20
 * @LastEditors: rooterShip
 * @LastEditTime: 2022-05-21 22:11:22
 */
/**
 * 116. 填充每个节点的下一个右侧节点指针
 * 给定一个 完美二叉树 ，其所有叶子节点都在同一层，每个父节点都有两个子节点。二叉树定义如下：
 * struct Node {
 *   int val;
 *   Node *left;
 *   Node *right;
 *   Node *next;
 * }
 * 
 * 填充它的每个 next 指针，让这个指针指向其下一个右侧节点。如果找不到下一个右侧节点，则将 next 指针设置为 NULL。
 * 初始状态下，所有 next 指针都被设置为 NULL。
 * 
 * 输入：root = [1,2,3,4,5,6,7]
 * 输出：[1,#,2,3,#,4,5,6,7,#]
 * 解释：给定二叉树如图 A 所示，你的函数应该填充它的每个 next 指针，
 * 以指向其下一个右侧节点，如图 B 所示。序列化的输出按层序遍历排列，
 * 同一层节点由 next 指针连接，'#' 标志着每一层的结束。
 * 
 * 输入：root = []
 * 输出：[]
 * 
 * tip: 相比于下一道题目 这个题目只是全子树的情况，但此处解法和下题完全相同，这两题构建了此类题目的通解
 */
package binaryTree;

import java.util.ArrayDeque;
import java.util.Deque;

public class Connect1 {
    public static Node Connect(Node root) {
        if (root == null) // 特殊判断，树为空直接返回
            return root;

        Deque<Node> deque = new ArrayDeque<>(); // 跑BFS必备储存节点的队列
        deque.add(root); // 将根节点压入队列

        while (!deque.isEmpty()) {
            Node pre = null; // 初始状态下，所有 next 指针都被设置为 NULL。
            int levelSize = deque.size(); // 上一层的节点个数
            
            while (levelSize != 0) { // 遍历每层所有的节点
                Node curNode = deque.poll(); // 弹出当前节点

                /**如果pre不为null，说明不是第一个节点. 这句判断能保证
                当curNode为该层第一个节点时，能跳过这段判断直接更新pre
                否则在if里面直接让pre.next = curNode是无意义的
                加了判断后，让当前节点被前一个节点指向*/
                if (pre != null) { 
                    pre.next = curNode;
                }
                pre = curNode;
                if (curNode.left != null) { //广度（BFS）扩张
                    deque.add(curNode.left);
                }
                if (curNode.right != null) {
                    deque.add(curNode.right);
                }
                levelSize--;
            }
        }
        return root;
    }

    public static void main(String[] args) {

    }
}
