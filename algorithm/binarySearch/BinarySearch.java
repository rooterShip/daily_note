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
        else if(key>a[mid]) hi = mid + 1;
        else return mid;
      }
      return -1;
    }
    public static void main(String[] args) {
      Scanner scanner = new Scanner(System.in);
      String str = scanner.next().toString();
      String[] temp = str.split(" ");
      int[] b = new int[temp.length];
      for(int i = 0; i<=temp.length-1; i++)
          b[i] = Integer.parseInt(temp[i]);
      Arrays.sort(b);
    }
  }