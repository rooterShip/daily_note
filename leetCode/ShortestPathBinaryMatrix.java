import java.util.ArrayDeque;
//import java.util.Arrays;
import java.util.Deque;

/*
 * @Author: rooterShip
 * @Date: 2022-04-26 22:01:37
 * @LastEditors: rooterShip
 * @LastEditTime: 2022-04-26 23:01:29
 */
/**
 * 给你一个 n x n 的二进制矩阵 grid 中，返回矩阵中最短 畅通路径 的长度。如果不存在这样的路径，返回 -1 。
 * 二进制矩阵中的 畅通路径 是一条从 左上角 单元格（即，(0, 0)）到 右下角 单元格（即，(n - 1, n - 1)）的路径，该路径同时满足下述要求：
 * 路径途经的所有单元格都的值都是 0 。
 * 路径中所有相邻的单元格应当在 8 个方向之一 上连通（即，相邻两单元之间彼此不同且共享一条边或者一个角）。
 * 畅通路径的长度 是该路径途经的单元格总数。
 * 
 * 输入：grid = [[0,1],[1,0]]
 * 输出：2
 * 
 * 输入：grid = [[0,0,0],[1,1,0],[1,1,0]]
 * 输出：4
 * 
 * 输入：grid = [[1,0,0],[1,1,0],[1,1,0]]
 * 输出：-1
 */
public class ShortestPathBinaryMatrix {
    public static int shortestPathBinaryMatrix(int[][] grid){
        //特判
        int len = grid.length;
        if(grid[0][0]==1) return -1;
        if(len == 1) return 1;
        //标注八个方向，等下for循环遍历加入队列中
        int[][] dir = {{0,1},{0,-1},{1,0},{-1,0},{-1,-1},{1,1},{-1,1},{1,-1}};

        //BFS从（0，0）开始BFS
        int cnt = 1;
        Deque<int[]> deque = new ArrayDeque<>();
        deque.add(new int[]{0,0});
        grid[0][0] = 0;
        while(!deque.isEmpty()){
            int[] cur = deque.poll();
            int i = cur[0];
            int j = cur[1];
            if(i==len-1 && j==len-1)
                return cnt;
            //@TODO 判断八个方向，如果满足 不超过边界&&值为0 就加入队列，且置为1
        }

        return -1;
    }
    public static void main(String[] args) {
        int[][] grid = {{0,0,0},{1,1,0},{1,1,0}};
        System.out.println(shortestPathBinaryMatrix(grid));
    }
}
