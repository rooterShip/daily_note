/*
 * @Author: Rooter
 * @Date: 2022-04-01 22:26:19
 * @LastEditors: Rooter
 * @LastEditTime: 2022-04-05 22:30:53
 */
/**
 * 给定一组非负整数 nums，重新排列每个数的顺序（每个数不可拆分）使之组成一个最大的整数。
 * 注意：输出结果可能非常大，所以你需要返回一个字符串而不是整数。
 * 
 * 输入：nums = [10,2]
 * 输出："210"
 * 
 * 输入：nums = [3,30,34,5,9] 10,2,9,39,17
 * 输出："9534330"
 */
public class LargestNumber2 {
    public static String largestNumber(int[] nums){
        String res = "";
        String[] temp = new String[nums.length];
        for(int i = 0; i < nums.length; i++){
            temp[i] = String.valueOf(nums[i]);
        }
        if(temp.length==1) return temp[0];
        for(int i = temp.length - 1; i > 0; i--){
            if((temp[i] + temp[i-1]).compareTo(temp[i-1] + temp[i])>0){
                String mid = temp[i];
                temp[i] = temp[i-1];
                temp[i-1] = mid;
            }
        }
        for(int i = 0; i < temp.length; i++){
            res = res + temp[i];
        }
        return res;
    }
    public static void main(String[] args) {
        int[] nums = {3,30,34,5,9};
        System.out.println(largestNumber(nums));
    }
}
