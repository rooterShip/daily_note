import java.util.HashMap;
import java.util.Map;

/*
 * @Author: Rooter
 * @Date: 2022-03-19 22:20:14
 * @LastEditors: Rooter
 * @LastEditTime: 2022-03-19 22:32:12
 */
/**
 * 给定一个包含 [0, n] 中 n 个数的数组 nums ，找出 [0, n] 这个范围内没有出现在数组中的那个数。
 * 
 * 输入：nums = [3,0,1]
 * 输出：2
 * 解释：n = 3，因为有 3 个数字，所以所有的数字都在范围 [0,3] 内。2 是丢失的数字，因为它没有出现在 nums 中。
 * 
 * 输入：nums = [0,1]
 * 输出：2
 * 解释：n = 2，因为有 2 个数字，所以所有的数字都在范围 [0,2] 内。2 是丢失的数字，因为它没有出现在 nums 中。
 * 
 * 输入：nums = [9,6,4,2,3,5,7,0,1]
 * 输出：8
 * 解释：n = 9，因为有 9 个数字，所以所有的数字都在范围 [0,9] 内。8 是丢失的数字，因为它没有出现在 nums 中。
 * 
 * 输入：nums = [0]
 * 输出：1
 * 解释：n = 1，因为有 1 个数字，所以所有的数字都在范围 [0,1] 内。1 是丢失的数字，因为它没有出现在 nums 中。
 */
public class MissingNumber {
    public static int missingNumber(int[] nums){
        int n = nums.length;
        Map<Integer,Integer> map = new HashMap<>();
        for(int i = 0; i < n+1; i++){
            map.put(i, 0);
        }
        for(int i = 0; i < n; i++){
            if(map.containsKey(nums[i])){
                map.put(nums[i], map.get(nums[i])+1);
            }
        }
        for(int key : map.keySet()){
            if(map.get(key) == 0){
                return key;
            }
        }
        return 0;
    }
    public static void main(String[] args) {
        int[] nums = {3,0,1};
        System.out.println(missingNumber(nums));
    }
}