import java.util.Arrays;

/*
 * @Author: Rooter
 * @Date: 2022-01-25 15:53:07
 * @LastEditors: Rooter
 * @LastEditTime: 2022-01-25 16:53:24
 */

public class ClosestSum3 {
    public static int threeSumClosest(int[] nums, int target){
        Arrays.sort(nums);
        int len = nums.length;
        int ans = nums[0] + nums[1] + nums[2];
        for(int i = 0; i < len - 2; i++){
            if(i>0 && nums[i-1]==nums[i]) continue;
            int lef = i + 1;
            int reg = len - 1;
            while(lef<reg){
                int sum = nums[i] + nums[lef] + nums[reg];
                if(Math.abs(sum-target) < Math.abs(ans-target)){ //多加一条判断语句（如果嵌入到下面的判断语句中会超时）
                    ans = sum;
                } 
                if(sum==target) return target; //双指针判断大小与三数之和相同
                else if(sum > target){
                    reg--;
                }
                else if(sum < target){
                    lef++;
                } 
            }
        }
        return ans;
    }
    public static void main(String[] args) {
        int[] nums = {0,0,0};
        int target = 1;
        int sum = threeSumClosest(nums, target);
        System.out.println(sum); 
    }
}
