import java.util.HashSet;
import java.util.Set;

/*
 * @Author: Rooter
 * @Date: 2022-03-15 21:52:31
 * @LastEditors: Rooter
 * @LastEditTime: 2022-03-15 22:30:07
 */
/**
 * 给你一个整数数组 nums 。如果任一值在数组中出现至少两次 ，返回 true ；如果数组中每个元素互不相同，返回 false 。
 * 
 * 输入：nums = [1,2,3,1]
 * 输出：true
 * 
 * 输入：nums = [1,2,3,4]
 * 输出：false
 * 
 * 输入：nums = [1,1,1,3,3,4,3,2,4,2]
 * 输出：true
 * 
 * 笔者注：类似于这类查找题目，一定要用哈希表(hashmap hashset)比list查找快速
 */
public class ContainsDuplicate {
    public static boolean containsDuplicate(int[] nums){
        Set<Integer> set = new HashSet<>();
        for(int x : nums){
            if(!set.add(x)) return true;
            else set.add(x);
        }
        return false;
    }
    public static void main(String[] args) {
        int[] nums = {1,2,3,4};
        System.out.println(containsDuplicate(nums));
    }
}