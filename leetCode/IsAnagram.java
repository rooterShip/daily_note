import java.util.Arrays;

/*
 * @Author: Rooter
 * @Date: 2022-03-18 21:29:28
 * @LastEditors: Rooter
 * @LastEditTime: 2022-03-18 22:00:15
 */
/**
 * 给定两个字符串 s 和 t ，编写一个函数来判断 t 是否是 s 的字母异位词。
 * 注意：若 s 和 t 中每个字符出现的次数都相同，则称 s 和 t 互为字母异位词。
 * 
 * 输入: s = "anagram", t = "nagaram"
 * 输出: true
 * 
 * 输入: s = "rat", t = "car"
 * 输出: false
 */
public class IsAnagram {
    public static boolean isAnagram(String s, String t){
        //为减少时间复杂度可以多加一步判断字符串长度的判断语句
        if(s.length() != t.length()) return false;

        char[] s1 = s.toCharArray();
        char[] t1 = t.toCharArray();
        Arrays.sort(s1);
        Arrays.sort(t1);
        return Arrays.equals(s1, t1);
    }
    public static void main(String[] args) {
        String s = "nagara";
        String t = "nagaram";
        System.out.println(isAnagram(s, t));
    }
}