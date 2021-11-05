# 初级排序算法
- 排序算法类模板
  ```java
  public class Example{
      public static void sort(Comparable[] a){

      }

      public static boolean less(Comparable v, Comparable w){
          return v.compareTo(w) < 0; //利用compareTo函数，小于0说明v<w
      }

      public static void exch(Comprable[] a, int i, int j){
          //交换位置
          comparable t = a[i];
          a[i] = a[j];
          a[j] = t;
      }

      private static void show(Comparable[] a){
          //在单行中打印数组
          for(int i = 0; i<a.length; i++){
              System.out.println(a[i]+' ');
          }
          System.out.println();
      }

      public static boolean isSorted(Comparable[] a){
          //测试数组元素是否有序
          for(int i = 1; i<a.length; i++)
            if(less(a[i], a[i-1])) return false;
        return true;
      }

      public static void main(String[] args){
          Scanner sc = new Scanner(System.in);
          String str1 = sc.next().toString();
          String[] str2 = str1.split(",");
          sort(str2);
          assert isSorted(a); //断言，检查是否排序成功
          show(a);
      }
  }
  ```