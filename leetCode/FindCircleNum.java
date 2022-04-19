import java.util.ArrayDeque;
import java.util.Deque;

/*
 * @Author: Rooter
 * @Date: 2022-04-19 22:06:50
 * @LastEditors: Rooter
 * @LastEditTime: 2022-04-19 22:33:02
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
        int cnt = 0;
        int row = isConnected.length;
        int col = isConnected[0].length;

        Deque<int[]> deque = new ArrayDeque<>();
        for(int i = 0; i < row; i++){
            for(int j = 0; j < col; j++){
                if(isConnected[i][j]==1){
                    cnt++;
                    deque.add(new int[]{i,j});
                }

                while(!deque.isEmpty()){
                    int[] cur = deque.poll();
                    if(cur[0]-1>=0 && isConnected[cur[0]-1][cur[1]]==1){
                        deque.add(new int[]{cur[0]-1,cur[1]});
                        isConnected[cur[0]-1][cur[1]]=0;
                    }

                    if(cur[0]+1<=col-1 && isConnected[cur[0]+1][cur[1]]==1){
                        deque.add(new int[]{cur[0]+1,cur[1]});
                        isConnected[cur[0]+1][cur[1]]=0;
                    }

                    if(cur[1]-1>=0 && isConnected[cur[0]][cur[1]-1]==1){
                        deque.add(new int[]{cur[0],cur[1]-1});
                        isConnected[cur[0]][cur[1]-1]=0;
                    }

                    if(cur[1]+1<=row-1 && isConnected[cur[0]][cur[1]+1]==1){
                        deque.add(new int[]{cur[0],cur[1]+1});
                        isConnected[cur[0]][cur[1]+1]=0;
                    }
                }
            }
        }
        return cnt;
    }
    public static void main(String[] args) {
        int[][] isConnected = {{1,0,0,1},{0,1,1,0},{0,1,1,1},{1,0,1,1}};
        System.out.println(findCircleNum(isConnected));
    }
}