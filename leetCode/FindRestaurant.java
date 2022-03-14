import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 * @Author: Rooter
 * @Date: 2022-03-14 21:52:51
 * @LastEditors: Rooter
 * @LastEditTime: 2022-03-14 22:26:21
 */
/**
 * 假设 Andy 和 Doris 想在晚餐时选择一家餐厅，并且他们都有一个表示最喜爱餐厅的列表，每个餐厅的名字用字符串表示。
 * 你需要帮助他们用最少的索引和找出他们共同喜爱的餐厅。 如果答案不止一个，则输出所有答案并且不考虑顺序。 
 * 你可以假设答案总是存在。
 * 
 * 输入: list1 = ["Shogun", "Tapioca Express", "Burger King", "KFC"]，list2 = ["Piatti", "The Grill at Torrey Pines", "Hungry Hunter Steakhouse", "Shogun"]
 * 输出: ["Shogun"]
 * 解释: 他们唯一共同喜爱的餐厅是“Shogun”
 * 
 * 输入:list1 = ["Shogun", "Tapioca Express", "Burger King", "KFC"]，list2 = ["KFC", "Shogun", "Burger King"]
 * 输出: ["Shogun"]
 * 解释: 他们共同喜爱且具有最小索引和的餐厅是“Shogun”，它有最小的索引和1(0+1)。
 */
public class FindRestaurant {
    public static String[] findRestaurant(String[] list1,String[] list2){
        Map<String,Integer> map = new HashMap<>();
        List<String> list = new ArrayList<>();
        int min = Integer.MAX_VALUE;
        for(int i = 0; i < list1.length; i++){
            map.put(list1[i],i);
        }
        for(int i = 0; i < list2.length; i++){
            if(map.containsKey(list2[i])){
                if(min > map.get(list2[i]) + i){
                    min = map.get(list2[i]) + i;
                    list.clear();
                    list.add(list2[i]);
                }
                else if(min == map.get(list2[i]) + i){
                    list.add(list2[i]);
                }  
            }
        }
        String[] res = list.toArray(new String[list.size()]);
        return res;
    } 
    public static void main(String[] args) {
        String list1[] = {"Shogun", "Tapioca Express", "Burger King", "KFC"};
        String list2[] = {"Piatti", "The Grill at Torrey Pines", "Hungry Hunter Steakhouse", "Shogun"};
        System.out.println(Arrays.toString(findRestaurant(list1, list2)));
    }
    
}
