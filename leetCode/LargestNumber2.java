import java.util.Arrays;

/*
 * @Author: Rooter
 * @Date: 2022-04-01 22:26:19
 * @LastEditors: Rooter
 * @LastEditTime: 2022-04-06 22:18:38
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
 * 
 * 解答：https://leetcode-cn.com/problems/largest-number/solution/gong-shui-san-xie-noxiang-xin-ke-xue-xi-vn86e/
 */
public class LargestNumber2 {
    public static String largestNumber(int[] nums) {
            int n = nums.length;
            String[] ss = new String[n];
            for (int i = 0; i < n; i++) ss[i] = "" + nums[i];
            Arrays.sort(ss, (a, b) -> {
                String sa = a + b, sb = b + a ;
                return sb.compareTo(sa);
            });
            
            StringBuilder sb = new StringBuilder();
            for (String s : ss) sb.append(s);
            int len = sb.length();
            int k = 0;
            while (k < len - 1 && sb.charAt(k) == '0') k++;
            return sb.substring(k);
    }
    public static void main(String[] args) {
        int[] nums = {3,30,34,5,9};
        System.out.println(largestNumber(nums));
    }
}
