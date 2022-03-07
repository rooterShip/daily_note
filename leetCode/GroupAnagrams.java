import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 * @Author: Rooter
 * @Date: 2022-01-27 14:31:46
 * @LastEditors: Rooter
 * @LastEditTime: 2022-01-27 15:53:21
 */
/**
 * 给你一个字符串数组，请你将 字母异位词 组合在一起。可以按任意顺序返回结果列表。
 * 字母异位词 是由重新排列源单词的字母得到的一个新单词，所有源单词中的字母通常恰好只用一次
 * 
 * 输入: strs = ["eat", "tea", "tan", "ate", "nat", "bat"]
 * 输出: [["bat"],["nat","tan"],["ate","eat","tea"]]
 * 
 */
public class GroupAnagrams {
    public static List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> lists = new ArrayList<>();
        Map<String,List<String>> map = new HashMap<>();
        for(int i = 0; i < strs.length; i++){
            char[] arr = strs[i].toCharArray(); //将原本的字符串分割成一个个的字符，放在一个字符数组中
            Arrays.sort(arr); //将字符数组进行排序（按照acsll码--包含同样字符的数组排完序将会相同）
            String key = new String(arr);  //将排好序的字符数组合并成一个字符串。

            //突破点在于是一个key值对应着一组列表
            if(!map.containsKey(key)){ //如果key值没有被放入哈希表中，那么就新开一个key值对应的list，并将当前str压入list中
                List<String> list = new ArrayList<>();
                list.add(strs[i]);
                map.put(key, list);
            }
            else if(map.containsKey(key)){ //如果key值已经被放入哈希表中，那就找到该Key值对应的list，并把当前str放入对应的list中
                map.get(key).add(strs[i]);
            }
        }
        for(String str:map.keySet()){//遍历map的方法
            lists.add(map.get(str)); 
        }
        return lists;
    }
    public static void main(String[] args) {
        String[] strs = {"eat", "tea", "tan", "ate", "nat", "bat"};
        groupAnagrams(strs);
        for(int i = 0; i < groupAnagrams(strs).size(); i++){
            System.out.println(groupAnagrams(strs).get(i));
        }
    }
}
