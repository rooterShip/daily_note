/*
 * @Author: Rooter
 * @Date: 2022-02-23 16:43:47
 * @LastEditors: Rooter
 * @LastEditTime: 2022-02-23 18:17:50
 */
/**
 * 给你一个字符串 s ，根据下述规则反转字符串：
 * 所有非英文字母保留在原有位置。
 * 所有英文字母（小写或大写）位置反转。
 * 返回反转后的 s 。
 * 
 * 输入：s = "ab-cd"
 * 输出："dc-ba"
 */
/**
 * 利用双指针，对字符串进行两边判断，不是英文字母的直接跳过（Character.isLetter(ch)）
 * 两边都为字母的情况下交换，直到lef>=reg结束
 */
public class ReverseOnlyLetters {
    public static String reverseOnlystring(String s){
        int n = s.length();
        char[] temp = s.toCharArray(); //转换成字符数组，在后面利用Character.isLetter(ch)函数时可以直接填参数
        int lef = 0;
        int reg = n-1;
        while(lef<reg){
            while(lef<reg && !Character.isLetter(temp[lef])) lef++; //如果是非英文字母，直接跳过
            while(lef<reg && !Character.isLetter(temp[reg])) reg--; //如果是非英文字母，直接跳过
            if(lef<reg){  //能运行至此说明存在两端都是英文字母，交换即可，并移动双指针
                char tem = temp[lef];
                temp[lef] = temp[reg];
                temp[reg] = tem;
                lef++;
                reg--;
            }
        }
        return String.valueOf(temp); 
    }
    public static void main(String[] args) {
        String s = "a-bC-dEf-ghIj";
        String res = reverseOnlystring(s);
        System.out.println(res);
    }
}
