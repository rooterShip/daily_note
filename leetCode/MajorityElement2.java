import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 * @Author: Rooter
 * @Date: 2022-03-16 21:54:00
 * @LastEditors: Rooter
 * @LastEditTime: 2022-03-16 22:19:13
 */
/**
 * 给定一个大小为 n 的整数数组，找出其中所有出现超过 ⌊ n/3 ⌋ 次的元素。
 * 
 * 输入：[3,2,3]
 * 输出：[3]
 * 
 * 输入：nums = [1]
 * 输出：[1]
 * 
 * 输入：[1,1,1,3,3,2,2,2]
 * 输出：[1,2]
 */
public class MajorityElement2 {
    public static List<Integer> majorityElement2(int[] nums){
        int len = nums.length/3 + 1;
        List<Integer> list = new ArrayList<>();
        Map<Integer,Integer> map = new HashMap<>();
        for(int i = 0; i < nums.length; i++){
            if(map.containsKey(nums[i])){
                map.put(nums[i],map.get(nums[i]) + 1);
            }
            else{
                map.put(nums[i], 1);
            }
        }
        for(int key : map.keySet()){
            if(map.get(key) >= len){
                list.add(key);
            }
        }
        return list;
    }
    public static void main(String[] args) {
        int[] nums = {3,2,3};
        System.out.println(majorityElement2(nums).toString()); 
    }
}