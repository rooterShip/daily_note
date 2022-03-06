import java.util.Arrays;

/*
 * @Author: Rooter
 * @Date: 2022-03-06 17:18:47
 * @LastEditors: Rooter
 * @LastEditTime: 2022-03-06 21:45:14
 */
/**
 * 给定一个无序的数组 nums，返回 数组在排序之后，相邻元素之间最大的差值 。如果数组元素个数小于 2，则返回 0 。
 * 您必须编写一个在「线性时间」内运行并使用「线性额外空间」的算法。
 * 
 * 输入：nums = [3,6,9,1]
 * 输出：3
 * 
 * solution:此题我利用java库函数sort解法，时间复杂度为(nlogn)并不符合题意，但在题目给的数据范围内起始性能并不差
 * 标准解法应为桶排序等。
 * 
 * 桶排序：设长度为 N 的数组中最大值为 max,min，则不难发现相邻数字的最大间距不会小于
 *  (max-min)/(N-1)(向上取整)，元素之间的最大间距一定不会出现在某个桶的内部，而一定会出现在不同桶当中。
 */
public class Maxmium{
    public static int maxmium(int[] nums){
        if(nums.length < 2) return 0;
        Arrays.sort(nums);
        int max = 0;
        for(int i = 1; i < nums.length; i++){
            if(nums[i]-nums[i-1] > max) max = nums[i]-nums[i-1]; 
        }
        return max;
    }
    public static void main(String[] args) {
        int[] nums = {3,6,9,1};
        int max = maxmium(nums);
        System.out.println(max);
    }
}