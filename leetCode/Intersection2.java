import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/*
 * @Author: Rooter
 * @Date: 2022-03-21 22:24:09
 * @LastEditors: Rooter
 * @LastEditTime: 2022-03-24 22:51:39
 */
/**
 * 给你两个整数数组 nums1 和 nums2 ，请你以数组形式返回两数组的交集。返回结果中每个元素出现的次数，
 * 应与元素在两个数组中都出现的次数一致（如果出现次数不一致，则考虑取较小值）。可以不考虑输出结果的顺序。
 * 
 * 输入：nums1 = [1,2,2,1], nums2 = [2,2]
 * 输出：[2,2]
 * 
 * 输入：nums1 = [4,9,5], nums2 = [9,4,9,8,4]
 * 输出：[4,9]
 */
public class Intersection2 {
        public static int[] intersect(int[] nums1, int[] nums2) {
            if (nums1.length > nums2.length) {
                return intersect(nums2, nums1);
            }
            Map<Integer, Integer> map = new HashMap<Integer, Integer>();
            for (int num : nums1) {
                int count = map.getOrDefault(num, 0) + 1;
                map.put(num, count);
            }
            int[] intersection = new int[nums1.length];
            int index = 0;
            for (int num : nums2) {
                int count = map.getOrDefault(num, 0);
                if (count > 0) {
                    intersection[index++] = num;
                    count--;
                    if (count > 0) {
                        map.put(num, count);
                    } else {
                        map.remove(num);
                    }
                }
            }
            return Arrays.copyOfRange(intersection, 0, index);
        }

        public static void main(String[] args) {
            int[] nums1 = {1,2,2,1};
            int[] nums2 = {2,2};
            System.out.println(Arrays.toString(intersect(nums1, nums2)));
        }
    }
