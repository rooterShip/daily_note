/*
 * @Author: Rooter
 * @Date: 2022-04-14 22:16:41
 * @LastEditors: Rooter
 * @LastEditTime: 2022-04-16 21:10:08
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
 * {1,3,5,7},{10,11,16,20},{23,30,34,60}
 * 输出：true
 * 
 * [[1,3,5,7],[10,11,16,20],[23,30,34,60]] target = 13
 */
public class SearchMatrix{
    public static boolean searchMatrix(int[][] matrix, int target){
                int rowIndex = binarySearchFirstColumn(matrix, target);
                if (rowIndex < 0) {
                    return false;
                }
                return binarySearchRow(matrix[rowIndex], target);
    }
        
    public static int binarySearchFirstColumn(int[][] matrix, int target) {
            int low = -1, high = matrix.length - 1;
            while (low < high) {
                int mid = (high - low + 1) / 2 + low;
                if (matrix[mid][0] <= target) {
                    low = mid;
                } else {
                    high = mid - 1;
                }
            }
            return low;
    }
        
    public static boolean binarySearchRow(int[] row, int target) {
        int low = 0, high = row.length - 1;
            while (low <= high) {
                int mid = (high - low) / 2 + low;
                if (row[mid] == target) {
                    return true;
                } else if (row[mid] > target) {
                    high = mid - 1;
                } else {
                    low = mid + 1;
                }
            }
            return false;
    }
    public static void main(String[] args) {
        int[][] matrix = {{1,3,5,7},{10,11,16,20},{23,30,34,60}};
        int target = 3;
        System.out.println(searchMatrix(matrix, target));
    }
}