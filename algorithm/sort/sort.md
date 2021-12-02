# 初级排序算法

## 排序算法类模板

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
  
  "排序算法类模板"中二等Example类展示了我们的习惯约定；<br>
  将排序代码放在类的sort()方法中；<br>
  包含辅助函数less()和exch()--其中less方法对元素进行比较，exch方法将元素交换位置<br>

- 运行时间
    评估算法的性能：<br>
    计算各个排序算法在不同的随机输入下的基本操作的次数<br>
    用数据估计算法的相对性能并介绍在实验中验证这些猜想所使用的工具（于大多数实现，代码风格一致会使我们更容易做出对性能的合理猜想<br>
    在研究排序算法时，需要计算**比较**和**交换**的数量，对于不交换元素的算法，计算访问数组的次数
- 额外的内存使用<br>
    排序算法的额外内存开销和运行时间是同等重要的。<br>
    原地排序算法：除了函数调用所需的栈和固定数目的实例变量之外无需额外内存<br>
    其他排序算法：除了函数调用所需的栈和固定数目的实例变量之外需要额外内存<br>

## 选择排序

- 概念
    找到数组中最小的元素，将该元素和数组第一个元素交换位置(如果第一个元素就是最小的元素和自己交换<br>
    在剩下的元素中找到最小的元素，将它与数组的第二个元素交换位置<br>
    往复，直至将整个数组排序<br>

  Supplement:  
  >每次交换都能排定一个元素，因此交换的总次数是N => 算法的时间效率取决于比较的次数<br>
  > 对于长度为N的数组，选择排序需要大约N<sup>2</sup>/2次比较和N次交换
- Features：
  - **运行时间和输入无关**：指全部随机排列的数组和已经有序的数组的排序时间相等
  - **数据移动是最少的**：交换次数和数组大小呈线性关系
- 算法

  ```java
  public class Selection{
      public static void sort(Comparable[] a){
          //升序排列
            int N = a.length;
            for(int i = 0; i < N; i++){
                int min = i;
                for(int j = i + 1; j < N; j++)
                    if(less(a[j], a[min])) min = j;
                exch(a, i, min);
            }
      }
  }
  ```

## 插入排序

- description<br>
  每次插入一个元素时，需要将其余所有元素在插入之前都向移动一位。<br>
  与选择排序一样，当前索引左边的所有元素都是有序的，但他们的最终位置还不确定，为了给更小的元素腾出空间，它们可能会被移动。当索引达到数组的右端时，即数组排序完成。<br>
  不同于选择排序，插入排序所需的时间取决于输入中元素的初始顺序--对一个很大且其中的元素已经有序（或接近有序）的数组进行排序会比对随机顺序的数组或时逆序数组进行排序要块的多
  supplement:
  >对于随机排列的长度为N且主键不重复的数组，平均情况下插入排序需要~N<sup>2</sup>/4次比较以及~N<sup>2</sup>/4次交换。最坏情况下需要~N<sup>2</sup>/2次比较和~N<sup>2</sup>/2次交换，最好情况下需要N-1次比较和0次交换。
- 算法

  ```java
  public class Insertion{
      public static void sort(Comparable[] a){
          //将数组a[]按升序排列
          //由已经排好序的右面第一个数字和已经排好序的左边挨个比较
          int N = a.length;
          for(int i = 1; i < N; i++){  //i要从1开始是因为a[j-1]
              for(int j = i; j > 0 && less(a[j], a[j-1]); j--){
                  exch(a, j, j-1);
              }
          }
      }
  }
  ```

## 希尔排序

- description<br>
  是一种**基于插入排序的快速的排序算法**。对于大规模乱序数组插入排序很慢，因为它只会交换相邻的元素，因此元素只能一点一点地从数组的一端移动到另一端。希尔排序为了加快速度简单地改进了插入排序，交换不相邻的元素以对数组的局部进行排序，并最终用插入排序将局部有序的数组排序。<br>
  思想是：使数组中任意间隔为h的元素都是有序的。这样的数组被称为h有序数组。可以**理解为一个h有序数组就是h个互相独立的有序数组编织在一起组成的一个数组。**<br>
- 实现方法<br>
  将待排序的数组元素按下标的一定增量分组 ，分成多个子序列，然后对各个子序列进行直接插入排序算法排序；然后依次缩减增量再进行排序，直到增量为**1**时，进行最后一次直接插入排序，排序结束。
  >**增量d范围：** 1 <= d <= 待排序数组的长度<br>
  >**增量的取值：** 一般初次取序列的（数组）的一半为增量，以后每次减半，直到增量为1。
- 算法<br>
  
  ```java
  public class Shell{
      public static void sort(Comparable[] a){
        //将a[]按升序排列
        int N = a.length;
        int h = 1;
        while(h < N/3) h = 3 * h + 1; //1, 4, 13, 40, 121, 364, 1093...
        while(h >= 1){
          //将数组变为h有序
          for(int i = h; i < N; i++){
            //将a[i]插入到a[i-h],a[i-2*h],a[i-3*h]...之中
            //第三层循环保证了每一段的同一个位置都会比较到
            for(int j = i; j >= h && less(a[j]), a[j-h]); j -= h)
              exch(a, j, j-h);
          }
          h = h/3;
        }
      }
  }
  ```