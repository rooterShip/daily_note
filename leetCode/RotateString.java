
/*
 * @Author: Rooter
 * @Date: 2022-04-07 22:13:26
 * @LastEditors: Rooter
 * @LastEditTime: 2022-04-07 23:13:14
 */
/**
 * 给定两个字符串, s 和 goal。如果在若干次旋转操作之后，s 能变成 goal ，那么返回 true 。
 * s 的 旋转操作 就是将 s 最左边的字符移动到最右边。 
 * 例如, 若 s = 'abcde'，在旋转一次之后结果就是'bcdea' 
 * 
 * 输入: s = "abcde", goal = "cdeab"
 * 输出: true
 * 
 * 输入: s = "abcde", goal = "abced"
 * 输出: false
 * 
 * 此中解法为笨拙之最，利用了最简单的循环将最前面的元素提到最后面
 * 
 * 妙解：宫水三叶：由于每次旋转操作都是将最左侧字符移动到最右侧，因此如果 goal 可由 s 经过多步旋转而来，
 * 那么 goal 必然会出现在 s + s 中，即满足 (s + s).contains(goal)，同时为了 s 本身过长导致的结果成立，我们需要先确保两字符串长度相等。
 */
public class RotateString {
    public static boolean rotateString(String s, String goal){
        int n = s.length();
        int cnt = 0; 
        char[] temp1 = s.toCharArray();
        while(cnt++!=n){
            char mid=temp1[0];
            for(int i = 0; i < temp1.length-1; i++){
                temp1[i] = temp1[i+1];
            }
            temp1[temp1.length-1] = mid;
            s = String.valueOf(temp1);
            // System.out.println(s);
            if(s.equals(goal)) return true; //注意区别equals和==的区别
        }
        return false;
    }
    public static void main(String[] args) {
        String s = "abcde";
        String goal = "cdeab";
        System.out.println(rotateString(s, goal));
    }
}