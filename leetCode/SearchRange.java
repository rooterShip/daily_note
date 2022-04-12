import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
 * @Author: Rooter
 * @Date: 2022-04-09 22:23:40
 * @LastEditors: Rooter
 * @LastEditTime: 2022-04-12 22:53:07
 */
/**
 * 给定一个按照升序排列的整数数组 nums，和一个目标值 target。找出给定目标值在数组中的开始位置和结束位置。
 * 如果数组中不存在目标值 target，返回 [-1, -1]。
 * 
 * 输入：nums = [5,7,7,8,8,10], target = 8
 * 输出：[3,4]
 * 
 * 输入：nums = [5,7,7,8,8,10], target = 6
 * 输出：[-1,-1]
 * 
 * 输入：nums = [], target = 0
 * 输出：[-1,-1]
 */
public class SearchRange {
    public static int[] searchRange(int[] nums,int target){
        List<Integer> list = new ArrayList<>();
        for(int i = 0; i < nums.length; i++){
            if(nums[i]==target){
                list.add(i);
            }
        }
        if(list.isEmpty()) return new int[] {-1,-1};
        int[] ans = new int[2];
        ans[0] = list.get(0);
        ans[1] = list.get(list.size()-1);
        return ans;
    }
    public static void main(String[] args) {
        int[] nums = {5,7,7,8,8,10};
        int target = 6;
        System.out.println(Arrays.toString(searchRange(nums, target)));
    }
}