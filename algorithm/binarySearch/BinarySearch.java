//输入待查数字列表和待查数字
//排序待查数字列表
//将待查数字和数字列表传入二分查找函数

package algorithm.binarySearch;

import java.util.Arrays;
import java.util.Scanner;

class BinarySearch{
    public static int rank(int key,int[] a){
        //保证数组有序
        int lo = 0;
        int hi = a.length-1;
        while(lo <= hi){
          //被查找的键要么不存在，要么必然存在于a[lo...hi]之中
          int mid = lo+(hi-lo)/2;
          if(key<a[mid]) hi = mid - 1;
          else if(key>a[mid]) lo = mid + 1;
          else return mid;
        }
        return -1;
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String str = scanner.next().toString();
        String str2 = scanner.next().toString(); //不定长数组输入的方法，先赋值给字符串

        String[] temp = str.split(",");
        String[] temp2 = str2.split(","); //将字符串以“，”分隔存入字符串数组

        int[] key = new int[temp2.length];
        int[] b = new int[temp.length]; 

        for(int i = 0;i < temp2.length;i++)
            key[i] = Integer.parseInt(temp2[i]); 
        for(int i = 0; i<=temp.length-1; i++)
            b[i] = Integer.parseInt(temp[i]); //将字符串数组赋值给整型数组
            
        Arrays.sort(b); //排序
        scanner.close();
        for(int i = 0; i < key.length; i++){
          if(rank(key[i],b)<0){
            System.out.println(key[i]);  
        }
      }
  }
}