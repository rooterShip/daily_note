
/*
 * @Author: rooterShip
 * 
 * @Date: 2022-06-09 22:16:34
 * 
 * @LastEditors: rooterShip
 * 
 * @LastEditTime: 2022-06-10 22:42:17
 */
/**
 * 96. 不同的二叉搜索树
 * 给你一个整数 n ，求恰由 n 个节点组成且节点值从 1 到 n 互不相同的 二叉搜索树 有多少种？
 * 返回满足题意的二叉搜索树的种数。
 */
package binaryTree;

import java.util.HashMap;
import java.util.Map;

public class NumTrees {
    public static int numTrees(int n) {

        // 如果只有0，或者1个节点，则可能的子树情况为1种
        if (n == 0 || n == 1) {
            return 1;
        }

        /**
         * 由于在暴力递归的时候会重复算很多次已经
         * 算过的子树的情况：比如[1,2,3]的情况当1当
         * 根节点和3当根节点的情况下都会算到2为子树的情况
         * 则利用哈希表将其值存储下来，用到的话直接取。
         */
        Map<Integer,Integer> map = new HashMap<>();
        if(map.containsKey(n)){
            return map.get(n);
        }

        int count = 0;
        for (int i = 0; i < n; i++) {
            // 暴力递归 把左边所有的可能与右边所有的可能进行笛卡尔积
            int leftnums = numTrees(i);
            int rightnums = numTrees(n - i - 1);

            count += leftnums * rightnums;
        }
        map.put(n, count);
        return count;

    }

    public static void main(String[] args) {

    }
}