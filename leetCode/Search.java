
/*
 * @Author: Rooter
 * @Date: 2022-04-13 21:47:20
 * @LastEditors: Rooter
 * @LastEditTime: 2022-04-13 21:56:10
 */
/**
 * 整数数组 nums 按升序排列，数组中的值 互不相同 。
 * 在传递给函数之前，nums 在预先未知的某个下标 k（0 <= k < nums.length）上进行了 旋转，使数组变为 [nums[k], nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]]（下标 从 0 开始 计数）。例如， [0,1,2,4,5,6,7] 在下标 3 处经旋转后可能变为 [4,5,6,7,0,1,2] 。
 * 给你 旋转后 的数组 nums 和一个整数 target ，如果 nums 中存在这个目标值 target ，则返回它的下标，否则返回 -1 。
 * 
 * 输入：nums = [4,5,6,7,0,1,2], target = 0
 * 输出：4
 * 
 * 输入：nums = [4,5,6,7,0,1,2], target = 3
 * 输出：-1
 * 
 * 输入：nums = [1], target = 0
 * 输出：-1
 */
public class Search {
    public static int search (int[] nums, int target){
        for(int i = 0; i < nums.length; i++){
            if(nums[i]==target) return i;
        }
        return -1;
    }
    public static void main(String[] args) {
        
    }
}