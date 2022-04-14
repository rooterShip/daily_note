/*
 * @Author: Rooter
 * @Date: 2022-04-14 22:16:41
 * @LastEditors: Rooter
 * @LastEditTime: 2022-04-14 23:13:06
 */
/**
 * 编写一个高效的算法来判断 m x n 矩阵中，是否存在一个目标值。该矩阵具有如下特性：
 * 每行中的整数从左到右按升序排列。
 * 每行的第一个整数大于前一行的最后一个整数。
 * 
 * 1 3 5 7
 * 10 11 16 20
 * 23 30 34 60
 * 输入：matrix = [[1,3,5,7],[10,11,16,20],[23,30,34,60]], target = 3
 * 输出：true
 */
public class SearchMatrix{
    public static boolean searchMatrix(int[][] matrix, int target){
        int row[] = new int[matrix.length];
        for(int i = 0; i < matrix.length; i++){
            row[i] = matrix[i][0];
        }
        int lef = 0;
        int reg = row.length - 1;
        while(reg-lef!=1){
            int mid = (lef + reg)/2;
            if(target > row[mid]){
                lef = mid;
            }
            else if(target < row[mid]){
                reg = mid;
            }
            else return true;
        }
        for(int i = 0; i < matrix[lef].length; i++){
            if(target==matrix[lef][i]) return true;
        }
        return false;
    }
    public static void main(String[] args) {
        int[][] matrix = {{1,3,5,7},{10,11,16,20},{23,30,34,60}};
        int target = 3;
        System.out.println(searchMatrix(matrix, target));
    }
}