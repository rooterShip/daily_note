
/*
 * @Author: Rooter
 * @Date: 2022-04-21 22:46:09
 * @LastEditors: Rooter
 * @LastEditTime: 2022-04-21 23:03:00
 */
/**
 * 给定一个二叉树
 * struct Node {
 *   int val;
 *   Node *left;
 *   Node *right;
 *   Node *next;
 * }
 * 填充它的每个 next 指针，让这个指针指向其下一个右侧节点。如果找不到下一个右侧节点，则将 next 指针设置为 NULL。
 * 初始状态下，所有 next 指针都被设置为 NULL。
 */
public class Connect {
    public static Node connect(Node root){
        return root;
    }
    public static void main(String[] args) {
        
    }
}

class Node{
    public int val;
    public Node left;
    public Node right;
    public Node next;

    public Node(){};
    
    public Node(int _val){
        val = _val;
    }

    public Node(int _val,Node _left,Node _right,Node _next){
        val = _val;
        left = _left;
        right = _right;
        next = _next;
    }
}