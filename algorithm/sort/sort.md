# 初级排序算法
## - 排序算法类模板
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
  
  >"排序算法类模板"中二等Example类展示了我们的习惯约定；<br>
  >将排序代码放在类的sort()方法中；<br>
  >包含辅助函数less()和exch()--其中less方法对元素进行比较，exch方法将元素交换位置<br>
- 运行时间
>评估算法的性能：<br>
>计算各个排序算法在不同的随机输入下的基本操作的次数<br>
>用数据估计算法的相对性能并介绍在实验中验证这些猜想所使用的工具（于大多数实现，代码风格一致会使我们更容易做出对性能的合理猜想<br>
>在研究排序算法时，需要计算**比较**和**交换**的数量，对于不交换元素的算法，计算访问数组的次数
- 额外的内存使用<br>
>排序算法的额外内存开销和运行时间是同等重要的。<br>
>原地排序算法：除了函数调用所需的栈和固定数目的实例变量之外无需额外内存<br>
>其他排序算法：除了函数调用所需的栈和固定数目的实例变量之外需要额外内存<br>
## 选择排序
- 概念
  >找到数组中最小的元素，将该元素和数组第一个元素交换位置(如果第一个元素就是最小的元素和自己交换<br>
  >在剩下的元素中找到最小的元素，将它与数组的第二个元素交换位置<br>
  >往复，直至将整个数组排序
