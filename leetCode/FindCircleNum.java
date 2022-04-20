import java.util.ArrayDeque;
import java.util.Deque;

/*
 * @Author: Rooter
 * @Date: 2022-04-19 22:06:50
 * @LastEditors: Rooter
 * @LastEditTime: 2022-04-20 22:29:43
 */
/**
 * 
 * 有 n 个城市，其中一些彼此相连，另一些没有相连。如果城市 a 与城市 b 直接相连，且城市 b 与城市 c 直接相连，那么城市 a 与城市 c 间接相连。
 * 省份 是一组直接或间接相连的城市，组内不含其他没有相连的城市。
 * 给你一个 n x n 的矩阵 isConnected ，其中 isConnected[i][j] = 1 表示第 i 个城市和第 j 个城市直接相连，而 isConnected[i][j] = 0 表示二者不直接相连。
 * 返回矩阵中 省份 的数量。
 * 
 * 输入：isConnected = [[1,1,0],[1,1,0],[0,0,1]]
 * 输出：2
 * 
 * 输入：isConnected = [[1,0,0],[0,1,0],[0,0,1]] [[1,0,0,1],[0,1,1,0],[0,1,1,1],[1,0,1,1]]
 * 输出：3
 */
public class FindCircleNum {
    public static int findCircleNum(int[][] isConnected){
       int cities = isConnected.length;
       int cnt = 0;
       boolean[] visited = new boolean[cities];
       Deque<Integer> deque = new ArrayDeque<>();
       for(int i = 0; i < cities; i++){
           if(!visited[i]){
               //找到第一个没走过的节点，加入队列中并标记为已走过
               deque.add(i);
               visited[i]=true;

               //跑BFS，从当前节点发散找到相邻的节点，如果两节点之间有边(且发散到的节点没有被访问过），将该节点加入队列
               while(!deque.isEmpty()){
                   int cur = deque.poll();
                   for(int j = 0; j < cities; j++){
                       if(isConnected[cur][j]==1 && visited[j]==false){
                           deque.add(j);
                           visited[j]=true;
                       }
                   }
               }
               cnt++;
           }
       }
       return cnt;
    }
    public static void main(String[] args) {
        int[][] isConnected = {{1,0,0,1},{0,1,1,0},{0,1,1,1},{1,0,1,1}};
        System.out.println(findCircleNum(isConnected));
    }
}