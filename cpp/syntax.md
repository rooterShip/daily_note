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
  void push_back(const T& x); //
  iterator insert(iterator it,const T& x); //
  iterator insert(iterator it,int n,const T& x); //
  iterator insert(iterator it,const_iterator first,const_iterator last)
  //向量中迭代器指向元素前插入另一个相同类型向量的[first,last)间的数据
  ```
- 删除函数
  ```cpp
  iterator erase(iterator it); //删除向量中迭代器指向元素
  iterator erase(iterator first,iterator last);//删除向量中[first,last)元素
