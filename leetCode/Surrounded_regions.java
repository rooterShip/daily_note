import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;


/*
 * @Author: rooterShip
 * @Date: 2022-04-29 22:10:43
 * @LastEditors: rooterShip
 * @LastEditTime: 2022-04-29 23:03:58
 */
/**
 * 给你一个 m x n 的矩阵 board ，由若干字符 'X' 和 'O' ，
 * 找到所有被 'X' 围绕的区域，并将这些区域里所有的 'O' 用 'X' 填充。
 * "X","X","X","X"      "X","X","X","X"
 * "X","O","O","X"      "X","X","X","X"
 * "X","X","O","X"  =>  "X","X","X","X"
 * "X","O","X","X"      "X","O","X","X"
 * 
 * 输入：board = [["X","X","X","X"],["X","O","O","X"],["X","X","O","X"],["X","O","X","X"]]
 * 输出：[["X","X","X","X"],["X","X","X","X"],["X","X","X","X"],["X","O","X","X"]]
 * 解释：被围绕的区间不会存在于边界上，换句话说，任何边界上的 'O' 都不会被填充为 'X'。 
 * 任何不在边界上，或不与边界上的 'O' 相连的 'O' 最终都会被填充为 'X'。如果两个元素在水平或垂直方向相邻，则称它们是“相连”的。
 */
public class Surrounded_regions {
    public static char[][] solve(char[][] board){
        int len_x = board.length;
        int len_y = board[0].length;
        int[][] dir = {{1,0},{-1,0},{0,1},{0,-1}};

        //与边界'O'相连（直接或者间接）的'O'都不是被'X'包围的，遍历数组将边界'O'压入队列，并标记为其他的字母
        Deque<int[]> deque = new ArrayDeque<>();
        for(int i = 0; i < len_x; i++){
            if(board[i][0]=='O'){
                deque.add(new int[]{i,0});
                board[i][0] = '1';
            }
            if(board[i][len_y-1]=='O'){
                deque.add(new int[]{i,len_y-1});
                board[i][len_y-1] = '1';
            }
        }
        for(int j = 0; j < len_y; j++){
            if(board[0][j]=='O'){
                deque.add(new int[]{0,j});
                board[0][j]='1';
            }
            if(board[len_x-1][j]=='O'){
                deque.add(new int[]{len_x-1,j});
                board[len_x-1][j] = '1';
            }
        }

        //跑BFS，将与边界相连的坐标点都找出来，并标记为其他的字母
        while(!deque.isEmpty()){
            int[] cur = deque.poll();
            int r = cur[0];
            int c = cur[1];
            for(int i = 0; i < 4; i++){
                int x = dir[i][0] + r;
                int y = dir[i][1] + c;
                if(x>0 && x<len_x && y>0 && y<len_y && board[x][y]=='O'){
                    deque.add(new int[]{x,y});
                    board[x][y] = '1';
                }
            }
        }

        //遍历数组，如果还有'O'表示是剩下的被包围的，将其变为'X'，将标记为其他字母的坐标点改为'X'
        for(int i = 0; i < len_x; i++){
            for(int j = 0; j < len_y; j++){
                if(board[i][j]=='O') board[i][j] = 'X';
                if(board[i][j]=='1') board[i][j] = 'O';
            }
        }
        return board;
    }
    public static void main(String[] args) {
        char[][] board = {{'X','X','X','X'},{'X','O','O','X'},{'X','X','O','X'},{'X','O','X','X'}};
        System.out.println(Arrays.toString(solve(board)));
    }
}
