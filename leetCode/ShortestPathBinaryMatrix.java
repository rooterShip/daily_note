import java.util.ArrayDeque;
//import java.util.Arrays;
import java.util.Deque;

/*
 * @Author: rooterShip
 * @Date: 2022-04-26 22:01:37
 * @LastEditors: rooterShip
 * @LastEditTime: 2022-04-28 23:09:13
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
        Deque<int[]> deque = new ArrayDeque<>();
        deque.add(new int[]{0,0}); //将初始节点加入到节点中
        grid[0][0] = 1;//将初始节点设置为已经访问过
        int cnt = 1; //第一层必然会被访问到，所有step+1
        while(!deque.isEmpty()){
            int size = deque.size();//当前层的节点数，一个while循环结束即表示该层遍历结束即step+1
            while(size!=0){
                int[] cur = deque.poll(); //弹出当前节点
                int r = cur[0]; //将当前节点的x坐标赋值为r
                int c = cur[1]; //将当前节点的y坐标赋值为c
                if(r==len-1 && c==len-1) //设置返回机制，如果访问到的当前节点就是最后的目标节点，即返回当前的步数
                    return cnt;
                //判断八个方向，如果满足 不超过边界&&值为0 就加入队列，且置为1
                for(int i = 0; i < 8; i++){ //如果当前节点不是目标节点则遍历八个方向的节点，如果满足是0且不越界，加入队列，设置为1（已访问）
                    int x = dir[i][0] + r;
                    int y = dir[i][1] + c;
                    if(x<len && y<len && x>=0 && y>=0 && grid[x][y]==0){
                        grid[x][y] = 1;
                        deque.add(new int[]{x,y});
                    }
                }
                size = size - 1; //遍历过一个节点减去一个节点（当前层）
            }
            cnt++;//一层结束step+1
        }
        return -1;
    }
    public static void main(String[] args) {
        int[][] grid = {{0,0,0},{1,1,0},{1,1,0}};
        System.out.println(shortestPathBinaryMatrix(grid));
    }
}
