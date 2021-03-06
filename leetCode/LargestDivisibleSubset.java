import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
 * @Author: Rooter
 * @Date: 2022-03-28 21:45:49
 * @LastEditors: Rooter
 * @LastEditTime: 2022-03-28 22:48:26
 */
/**
 * 给你一个由 无重复 正整数组成的集合 nums ，请你找出并返回其中最大的整除子集 answer ，
 * 子集中每一元素对 (answer[i], answer[j]) 都应当满足：
 * answer[i] % answer[j] == 0 ，或
 * answer[j] % answer[i] == 0
 * 如果存在多个有效解子集，返回其中任何一个均可。
 * 
 * 输入：nums = [1,2,3]
 * 输出：[1,2]
 * 解释：[1,3] 也会被视为正确答案。
 * 
 * 输入：nums = [1,2,4,8]
 * 输出：[1,2,4,8]
 * 
 * 题解：https://leetcode-cn.com/problems/largest-divisible-subset/solution/gong-shui-san-xie-noxiang-xin-ke-xue-xi-0a3jc/
 *      何时能变成宫水那么强呢！
 */
public class LargestDivisibleSubset {
    public static List<Integer> largestDivisibleSubset(int[] nums){
        Arrays.sort(nums);
        int n = nums.length;
        int[] f = new int[n]; //定义 f[i] 为考虑前 i 个数字，且以第 i 个数为结尾的最长「整除子集」长度。
        int[] g = new int[n]; //用于后期回溯显示【轨迹】
        for(int i = 0; i < n; i++){
            //至少包含自身一个数，因此起始长度为1，由自身转移而来
            int len = 1, prev = i;
            for(int j = 0; j < i; j++){
                if(nums[i] % nums[j] == 0){
                    //如果能接在更长的序列后面，则更新【最大长度】&【从何转移而来】
                    if(f[j]+1 > len){
                        len = f[j] + 1;
                        prev = j;
                    }
                }
            }
            //记录【最终长度】&【从何转移而来】
            f[i] = len;
            g[i] = prev;
        }

        //遍历所有的f[i]，取得【最大长度】和【对应下标】
        int max = -1,idx = -1;
        for(int i = 0; i < n; i++){
            if(f[i] > max){
                idx = i;
                max = f[i];
            }
        }

        //使用g[]数组回溯出具体方案
        List<Integer> ans = new ArrayList<>();
        while(ans.size()!=max){
            ans.add(nums[idx]);
            idx = g[idx];
        }
        return ans;
    }
    public static void main(String[] args) {
        int[] nums = {9,18,54,90,108,180,360,540,720};
        System.out.println(largestDivisibleSubset(nums).toString());
    }
}
