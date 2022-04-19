import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

/*
 * @Author: Rooter
 * @Date: 2022-04-19 21:21:49
 * @LastEditors: Rooter
 * @LastEditTime: 2022-04-19 21:54:41
 */
/**
 * 
 * 给你一个字符串 s 和一个字符 c ，且 c 是 s 中出现过的字符。
 * 返回一个整数数组 answer ，其中 answer.length == s.length 且 answer[i] 是 s 中从下标 i 到离它 最近 的字符 c 的 距离 。
 * 两个下标 i 和 j 之间的 距离 为 abs(i - j) ，其中 abs 是绝对值函数。
 * 
 * 输入：s = "loveleetcode", c = "e"
 * 输出：[3,2,1,0,1,0,0,1,2,2,1,0]
 * 解释：字符 'e' 出现在下标 3、5、6 和 11 处（下标从 0 开始计数）。
 * 距下标 0 最近的 'e' 出现在下标 3 ，所以距离为 abs(0 - 3) = 3 。
 * 距下标 1 最近的 'e' 出现在下标 3 ，所以距离为 abs(1 - 3) = 2 。
 * 对于下标 4 ，出现在下标 3 和下标 5 处的 'e' 都离它最近，但距离是一样的 abs(4 - 3) == abs(4 - 5) = 1 。
 * 距下标 8 最近的 'e' 出现在下标 6 ，所以距离为 abs(8 - 6) = 2 。
 * 
 * 输入：s = "aaab", c = "b"
 * 输出：[3,2,1,0]
 */
public class ShortestToChar {
    public static int[] shortestToChar(String s, char c){
        int len = s.length();
        //将数组全部初始化为-1
        int[] ans = new int[len];
        for(int i = 0; i < len; i++){
            ans[i] = -1;
        }
        Deque<Integer> queue = new ArrayDeque<>();
        //将c的位置（始点）初始化为0，将起始点压入队列
        for(int i = 0; i < len; i++){
            if(s.charAt(i) == c){
                ans[i] = 0;
                queue.add(i);
            }
        }
        //从起始点出发进行BFS搜索（二维）
        while(!queue.isEmpty()){
            int cur = queue.poll();
            if(cur-1>=0 && ans[cur-1]==-1){
                queue.add(cur-1);
                ans[cur-1] = ans[cur] + 1;
            }
            if(cur+1<=len-1 && ans[cur+1]==-1){
                queue.add(cur+1);
                ans[cur+1] = ans[cur] + 1;
            }
        }
        return ans;
    }
    public static void main(String[] args) {
        String s = "loveleetcode";
        char c = 'e';
        System.out.println(Arrays.toString(shortestToChar(s, c)));
    }
}