import java.util.HashMap;
import java.util.Map;

/*
 * @Author: Rooter
 * @Date: 2022-03-09 22:25:05
 * @LastEditors: Rooter
 * @LastEditTime: 2022-03-09 23:31:31
 */
/**
 * 给定一个大小为 n 的数组，找到其中的多数元素。多数元素是指在数组中出现次数 大于 ⌊ n/2 ⌋ 的元素。
 * 你可以假设数组是非空的，并且给定的数组总是存在多数元素。
 * 
 * 输入：[2,2,1,1,1,2,2]
 * 输出：2
 * 
 * 输入：[3,2,3]
 * 输出：3
 */
public class MajorityElement {
    public static int majorityElement(int[] nums){
        int length = nums.length;
        int len = length/2 + 1;
        int ans = 0;
        Map<Integer,Integer> map = new HashMap<>();
        for(int i = 0; i < length; i++){
            if(map.containsKey(nums[i])){
                int value = map.get(nums[i]);
                value = value + 1;
                map.put(nums[i],value);
            }
            else{
                map.put(nums[i],1);
            }
        }
        for(Integer key:map.keySet()){  
            if(map.get(key) >= len)
                ans = key; 
        }
        return ans;  
    }
    public static void main(String[] args) {
        int[] nums = {2,2,1,1,1,1,2};
        int ans = majorityElement(nums);
        System.out.println(ans);
    }
}
