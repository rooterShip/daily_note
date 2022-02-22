import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
 * @Author: Rooter
 * @Date: 2022-01-21 14:37:49
 * @LastEditors: Rooter
 * @LastEditTime: 2022-01-21 20:08:00
 */
public class Sum3 {

    public static List<List<Integer>> threeSum(int[] nums){
        List<List<Integer>> lists = new ArrayList<>();
        //特判        
        int len = nums.length;
        if(len < 3) return lists; //长度小于3的时候不符合题意
        Arrays.sort(nums); //排序 复杂度为NlogN
        if(nums[0] > 0) return lists; //从小到大排序第一个数字大于0不可能相加为0

        for(int i = 0; i < len; i++){
            if(nums[i]>0) return lists; //降维后第一层循环判断如果大于0了，第二层循环在它之后的就不可能小于0了
            if(i>0 && nums[i]==nums[i-1]) continue; //因为该元素已经被判断过了，所以接下来就不再判断了，且相同情况下，前面的那个的遍历结果会包含后面的
            int lef = i+1;
            int reg = len-1;//双指针可以根据相加结果与target值进行比较灵活调节两边指针的移动方向
            while(lef < reg){
                int ans = nums[i] + nums[lef] + nums[reg];
                if(ans==0){
                    List<Integer> list = new ArrayList<>();
                    list.add(nums[i]);
                    list.add(nums[lef]);
                    list.add(nums[reg]);
                    lists.add(list);
                    while(lef<reg && nums[lef]==nums[lef+1]) lef++;
                    while(lef<reg && nums[reg]==nums[reg-1]) reg--;//去重
                    lef++;
                    reg--;
                }
                else if(ans>0) reg--;
                else if(ans<0) lef++;
            }
        }
        return lists;
    }
    public static void main(String[] args) {
        int[] nums = {0,0,0};
        List<List<Integer>> lists = new ArrayList<>();
        lists = threeSum(nums);
        int len = lists.size();
        System.out.println(len); 
        for(int i = 0; i < len; i++){
            System.out.println(lists.get(i));
        }
    }
}

