import java.util.Arrays;

/*
 * @Author: Rooter
 * @Date: 2022-03-14 18:58:27
 * @LastEditors: Rooter
 * @LastEditTime: 2022-03-14 19:06:16
 */
/**
 * 给定整数数组 nums 和整数 k，请返回数组中第 k 个最大的元素。
 * 请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。
 */
/**
 * 输入: [3,2,1,5,6,4] 和 k = 2
 * 输出: 5
 * 
 * 输入: [3,2,3,1,2,4,5,5,6] 和 k = 4
 * 输出: 4
 */
public class FindKthLargest {
    public static int findKthLargest(int[] nums, int k) {
        int len = nums.length;
        Arrays.sort(nums);
        System.out.println(Arrays.toString(nums));
        return nums[len-k]; 
    }
    public static void main(String[] args) {
        int[] nums = {3,2,3,1,2,4,5,5,6};
        System.out.println(findKthLargest(nums,4)); 
    }
}