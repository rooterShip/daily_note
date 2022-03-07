import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/*
 * @Author: Rooter
 * @Date: 2022-02-23 21:32:52
 * @LastEditors: Rooter
 * @LastEditTime: 2022-02-23 23:00:54
 */
/**
 * 以数组 intervals 表示若干个区间的集合，
 * 其中单个区间为 intervals[i] = [starti, endi] 。
 * 请你合并所有重叠的区间，并返回一个不重叠的区间数组，该数组需恰好覆盖输入中的所有区间 。
 */
/**
 * 输入：intervals = [[1,3],[2,6],[8,10],[15,18]]
 * 输出：[[1,6],[8,10],[15,18]]
 * 解释：区间 [1,3] 和 [2,6] 重叠, 将它们合并为 [1,6].
 */
public class MergeIntervals {
    public static int[][] merge(int[][] intervals){
        List<int[]> ans = new ArrayList<>();                             // Arrays.sort函数的第二个参数要传入一个实现Comparator的类来实现自定义排序，等同于下面操作
        Arrays.sort(intervals,new Comparator<int[]>(){                   // Comparator mycomparator = new MyComparator();
            public int compare(int[] interval1,int[] interval2){         // sort(intervals, mycomparator);
                return interval1[0]-interval2[0];                        // class MyComparator implements Comparator<int[]>{
            }                                                            //     public int compare(int[] interval1, int[] interval2){
        });                                                              //         return interval1[0] - interval2[0];
        int start = intervals[0][0];                                     //        }
        int end = intervals[0][1];                                       // }
        for(int i = 0; i < intervals.length; i++){
            if(intervals[i][0] <= end){
                end = Math.max(end, intervals[i][1]);
            }
            else{
                ans.add(new int[]{start,end});
                start = intervals[i][0];
                end = intervals[i][1];
            }
        }
        ans.add(new int[]{start,end});
        int[][] res = new int[ans.size()][2];  //将集合转换为数组
        for(int i = 0; i < res.length; i++){
            res[i] = ans.get(i);
        }
        return res;
    }
    public static void main(String[] args) {
        int[][] intervals = {{1,3},{2,6},{8,10},{15,18}};
        int[][] res = merge(intervals);
        for(int i = 0; i < res.length; i++){
            System.out.println(res[i]);
        }
    }
}
