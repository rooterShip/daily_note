import java.util.Arrays;

/*
 * @Author: Rooter
 * @Date: 2022-02-25 15:55:53
 * @LastEditors: Rooter
 * @LastEditTime: 2022-02-25 16:59:23
 */
/*
 * 给定一个包含红色、白色和蓝色、共 n 个元素的数组 nums ，
 * 原地对它们进行排序，使得相同颜色的元素相邻，并按照红色、白色、蓝色顺序排列。
 * 我们使用整数 0、 1 和 2 分别表示红色、白色和蓝色。
 * 必须在不使用库的sort函数的情况下解决这个问题。
 */
/**
 * 输入：nums = [2,0,2,1,1,0]
 * 输出：[0,0,1,1,2,2]
 */
/**
 * way1 利用冒泡排序
 * 
 */
public class SortColors {
    public static void sortColors(int[] nums){
        int len = nums.length;
        for(int i = 0; i < len - 1; i++)
            for(int j = 0; j < len -1 - i; j++){
                if(nums[j] > nums[j+1]){
                    int temp = nums[j];
                    nums[j] = nums[j+1];
                    nums[j+1] = temp;
                }
            }
        System.out.println(Arrays.toString(nums));
    }
    public static void main(String[] args) {
        int[] nums={2,0,2,1,1,0};
        sortColors(nums);
    }
}
