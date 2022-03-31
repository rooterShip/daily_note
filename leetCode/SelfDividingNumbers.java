import java.util.ArrayList;
import java.util.List;

/*
 * @Author: Rooter
 * @Date: 2022-03-31 22:30:40
 * @LastEditors: Rooter
 * @LastEditTime: 2022-03-31 22:59:52
 */
/**
 * 自除数 是指可以被它包含的每一位数整除的数。
 * 例如，128 是一个 自除数 ，因为 128 % 1 == 0，128 % 2 == 0，128 % 8 == 0。
 * 自除数 不允许包含 0 。
 * 给定两个整数 left 和 right ，返回一个列表，列表的元素是范围 [left, right] 内所有的 自除数 。
 * 
 * 输入：left = 1, right = 22
 * 输出：[1, 2, 3, 4, 5, 6, 7, 8, 9, 11, 12, 15, 22]
 * 
 * 输入：left = 47, right = 85
 * 输出：[48,55,66,77]
 */
public class SelfDividingNumbers {
    public static List<Integer> selfDividingNumbers(int left, int right) {
        List<Integer> list = new ArrayList<>();
        for(int i = left; i <= right; i++){
            int temp = i;
            int flag = 1;
            while(temp!=0){
                if(temp%10==0 || i%(temp%10)!=0){
                    flag = 0;
                    break;
                } 
                temp = temp/10;
            }
            if(flag==1){
                list.add(i);
            }
        }
        return list;
    }
    public static void main(String[] args) {
        int left = 47, right = 85;
        System.out.println(selfDividingNumbers(left, right).toString());
    }
}