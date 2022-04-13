import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
//sum4
/*
 * @Author: Rooter
 * @Date: 2022-01-26 16:43:14
 * @LastEditors: Rooter
 * @LastEditTime: 2022-04-13 12:02:58
 */
/**
 * Note--sum class
 * step 1: 排序（升序）
 * step 2: 特判：
 *              ①如果题目中不表示必定有解，特殊判断无解的可能性，求几个数之和的先判断给定数组中元素个数是否大于等于（满足）要求
 *              ②对于taget为非负数的情况下，先判断首元素（排完序）是否大于target，若大于则无解。
 * step 3: 降维+双指针
 *              ①第一层循环中进行特判，若target为非负数的情况，先判断正在遍历的元素是否大于target，若大于直接返回结果
 *              ②避免重复遍历，减小开销：在每一层遍历中（双指针之前的循环）要判断正在遍历的元素是否等于上一个已经遍历过的元素（由于已经排好序，所以相同的元素必然会排在一起）
 *               若相等，则直接跳过（continue）--在上一个元素遍历完之后，已经遍历完该元素的所有可能性。
 *              ③双指针操作将O(n^2)降到O(n)：当找到几个元素相加之和等于target的情况时，直接压入结果集当中并调整双指针的指向
 *               （while(lef<reg && nums[lef]==nums[lef++] lef++;
 *                 while(lef<reg && nums[reg]==nums[reg--] reg--;
 *                 lef++;
 *                 reg--;)
 *                当相加的结果大于target时，调整reg的指向（向左移），当相加的结果小于target时，调整lef的指向（向右移）
 * step 4: 返回结果集
 * 
 */
public class Sum4 {
    public static List<List<Integer>> fourSum(int[] nums, int target){
        List<List<Integer>> lists = new ArrayList<>();
        Arrays.sort(nums);
        int len = nums.length;
        if(len < 4 ) return lists;

        for(int i = 0; i < len-3; i++){
            if(i>0 && nums[i]==nums[i-1]) continue;
            for(int j = i+1; j < len-2; j++){
                if(j>i+1 && nums[j]==nums[j-1]) continue;
                int lef = j + 1;
                int reg = len - 1;
                while(lef<reg){
                    List<Integer> list = new ArrayList<>();
                    if(nums[i]+nums[j]+nums[lef]+nums[reg]==target){
                        list.add(nums[i]);
                        list.add(nums[j]);
                        list.add(nums[lef]);
                        list.add(nums[reg]);
                        lists.add(list);
                        while(lef<reg && nums[lef]==nums[lef+1]) lef++;
                        while(lef<reg && nums[reg]==nums[reg-1]) reg--;
                        lef++;
                        reg--;
                    }
                    else if(nums[i]+nums[j]+nums[lef]+nums[reg]<target) lef++;
                    else if(nums[i]+nums[j]+nums[lef]+nums[reg]>target) reg--;
                }
            }
        }
        return lists;
    } 
    public static void main(String[] args) {
        int[] nums = {1,-2,-5,-4,-3,3,3,5};
        int target = -11;
        List<List<Integer>> lists = fourSum(nums, target);
        int len = lists.size();
        for(int i = 0; i < len; i++){
            System.out.println(lists.get(i));
        }
    }
}
