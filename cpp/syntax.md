# vector
## Definition
向量(vector)是一个封装了动态大小数组的顺序容器(Sequence Container)。根任意其他类型容器一样，它能够存放各种类型的对象。即向量是一个能够存放任意类型的动态数组。
***
## 特性
- 顺序序列<br>
  顺序容器中的元素按照严格的**线性顺序**排序。可以通过元素在序列中的位置访问对应的元素。
- 动态数组<br>
  支持对序列中的任意元素进行快速直接访问，可以通过指针进行该操作。提供了在序列末尾相对快速地添加/删除元素的操作。
- Allocator-aware
  容器使用一个**内存分配器对象**来**动态**处理它的存储需求。
***
## 函数实现
- 构造函数
  ```cpp
  vector(); //创建一个空vector
  vector(int size); //创建一个vector，元素个数为size(这里的元素指对象元素)
  vector(int size,const t& t); //创建vector，元素个数为size，值均为t
  vector(const vector&); //复制构造函数
  vector(begin,end);//复制[begin,end)区间内另一个数组的元素到vector中
  ```
- 增加函数
  ```cpp
  void push_back(const T& x); //向量尾部增加一个元素x
  iterator insert(iterator it,const T& x); //向量迭代器指向元素前增加一个元素x
  iterator insert(iterator it,int n,const T& x); //向量迭代器指向元素前增加n个元素x
  iterator insert(iterator it,const_iterator first,const_iterator last)
  //向量中迭代器指向元素前插入另一个相同类型向量的[first,last)间的数据
  ```
- 删除函数
  ```cpp
  iterator erase(iterator it); //删除向量中迭代器指向元素
  iterator erase(iterator first,iterator last);//删除向量中[first,last)元素
  void pop_back(); //删除向量中最后一个元素
  void clear(); //清空向量中所有元素
  ```
- 遍历函数
  ```cpp
  reference at(int pos); //返货pos位置元素的引用
  reference front(); //返回首元素的引用
  reference back(); //返回尾元素的引用
  iterator begin(); //返回向量头指针
  iterator end(); //返回向量尾指针，指向向量最后一个元素的下一个位置
  reverse_iterator rbegin(); //反向迭代器，指向最后一个元素
  reverse_iterator rend(); //反向迭代器，指向第一个元素之前的位置
- 判断函数
  ```cpp
  bool empty() const; //判断向量是否为空，若为空，则向量中无元素
- 大小函数
  ```cpp
  int size() const; //返回向量中元素的个数
  int capacity() const; //返回当前向量所能容纳的最大元素值
  int max_size() const; //返回最大可也允许的vector元素数量值
  ```
  Supplement
  >capacity,如果不重新分配内存,当前已经分配的可以容纳的元素的个数<br>
  >max_size最大的可能的元素个数<br>
  >size是当前元素个数<br>
  >sizeof是vector本身的大小（sizeof(vector<int>)）<br>
- 其他函数
  ```cpp
  void swap(vector&); //交换两个同类型向量的数据
  void assign(int n,const T& x); //设置向量中前n个元素的值为x
  void assign(const_iterator first,const_iterator last);//向量中[first,last)中元素设置成当前向量元素
  ```
***
## 基本用法
```cpp
#inculde <vector>
using namespace std;
```
***
## 简单介绍
- vector<类型>标识符
  ```cpp
  vector<int>obj;
  ```
- vector<类型>标识符(最大容量)
  ```cpp
  vector<int>obj(5);
  ```
- ```cpp
  vector<vector<int>>v //二维向量
  ```
# Iterator
## 关于Iterator
>类似于指针是可以用来遍历存储空间连续的数据结构，iterator是对非连续空间的数据结构进行遍历,iterator(迭代器)是一种检查容器内元素并遍历元素的数据类型。iterator提供对一个容器中的对象(或string对象)的访问方法，并且定义了容器中对象的范围。iterator是pointer(指针)的泛化。<br>
***
## 迭代器和指针的区别
 >容器和string有**迭代器类型**同时拥有**返回迭代器的成员**。如：容器有成员begin和end，其中begin成员复制返回指向第一个元素的迭代器，而end成员返回指向容器**尾元素的下一个位置的迭代器**，即end指示的是一个不存在的元素，所以**end返回的是尾后迭代器**。
 ***
## 容器迭代器的使用 
>每种容器类型都定义了自己的迭代器类型
>每个容器类定义了一种名为itrerator类型，这种类型支持迭代器的各种行为。
```cpp
eg--vector<int>::iterator iter //定义了一种名为iter的变量，类型为iterator
```
## 各个迭代器的功能
| 迭代器类别    |    说明   |
| ----         |    ----   |
| 输入         |    从容器中读取元素。输入迭代器只能一次读入一个元素向前移动，输入迭代器只支持一遍算法，同一个输入迭代器不能两遍遍历一个序列   |
|输出| 向容器中写入元素。输出迭代器只能一次一个元素向前移动。输出迭代器只支持一遍算法，统一输出迭代器不能两次遍历一个序列|
|正向|组合输入迭代器和输出迭代器的功能，并保留在容器中的位置|
|双向|组合正向迭代器和逆向迭代器的功能，支持多遍算法|
|随机访问|组合双向迭代器的功能与直接访问容器中任何元素的功能，即可向前向后跳过任意个元素|
