import java.util.HashMap;
import java.util.Map;

/*
 * @Author: Rooter
 * @Date: 2022-01-21 17:32:07
 * @LastEditors: Rooter
 * @LastEditTime: 2022-01-22 16:03:51
 */

//要返回具体坐标（位置）时考虑用哈希表求解，用空间换时间
public class Sum2 {

    public static int[] twoSum(int[] nums, int target){
        int len = nums.length;
        Map<Integer,Integer> map = new HashMap<>(len-1); //创建哈希表
        map.put(nums[0], 0); //哈希表空的情况下无法查找，将第一个元素压入哈希表
        for(int i = 1; i < len; i++){
            if(map.containsKey(target-nums[i])){ //从第二个元素开始，判断之前的key值和本key值相加是否等于target
                return new int[]{map.get(target-nums[i]),i}; //存在的话直接返回value（把返回的值设为value）
            }
            map.put(nums[i], i); //不存在相加为target的key的情况下，把该key压入哈希表（默认只有一解）
        }
        return new int[0]; //不存在返回空数组
    }
    public static void main(String[] args) {
        int target = 9;
        int[] nums = {2,7,11,15};
        int[] temp = twoSum(nums, target);
        int len = temp.length;
        for(int i = 0; i < len; i++){
            System.out.println(temp[i]);
        }
    }
}

