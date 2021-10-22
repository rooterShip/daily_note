# 容器
## 容器的目的就是用来存储数据
***
>- java容器大致可以分为Set、List、Queue和Map四种(都位于<u>java.util</u>包下)<br>
>- Collection是List、Set、Queue的根接口，表示一组对象。
### 列表(List)：数组方式实现，维护元素的索引顺序
- 对象按索引存储
- 可以存储重复元素
- 主要实现类
  - -ArrayList:动态数组(遍历比较快，插入删除偏慢)
  - LinkedList:链表(双向链表，遍历速度慢，快速插入删除)
- ArrayList<br>
ArrayList就是动态数组，动态的增加和减少元素，可灵活的设置数组的大小<br>
  - 构造方法<br>
    -List list = new ArrayList();<br>
  - 其他方法<br>
    -add(E e):将指定的元素添加到此列表的尾部<br>
    -add(int index, E element):将指定的元素插入此列表中的指定位置<br>
    -remove(int index):移除此列表中指定位置上的元素<br>
    -remove(E e):移除此列表中指定元素<br>
    -get(int index):返回此列表中指定位置上的元素<br>
    -set(int index, E element):用指定的元素替代此列表中指定位置上的元素<br>
    -size():返回此列表中的元素数<br>
    -isEmpty():判断是否为空<br>
    -contains(E e):判断是否包含元素<br>
- ArrayList与Vector的区别
  - Vector是线程安全的，ArrayList不是线程安全的
  - ArrayList在底层数组不够用时在原来的基础上扩展0.5倍，Vector是扩展1倍
- LinkedList
  - 构造方法<br>
    -List names = new LinkedList();
***
### 集合(Set)：关心唯一性
- 对象无序存储
- 不能存储重复元素，不维护元素的索引顺序
- 主要实现类
  - HashSet:使用被插入对象的Hash码(效率最高)，**无序**，非同步，底层通过散列函数来进行元素的存储
  - LinkedHashSet:继承于HashSet、又基于LinkedHashMap来实现，遍历性能比HashSet好，在散列函数的基础上添加了链表支持。
  - TreeSet:二叉树结构，保证元素按照元素的自然顺序进行升序排序，添加操作速率比散列集慢，访问和遍历的时间很快。  