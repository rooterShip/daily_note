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
  - HashSet<br>
    - 构造方法<br>
      Set name = new HashSet();
    - 其他方法<br>
      –add(E e)：如果此 set 中尚未包含指定元素，则添加指定元素<br>
      –clear()：从此 set 中移除所有元素<br>
      –remove(Object o)：如果指定元素存在于此 set 中，则将其移除<br>
      –size()：返回此 set 中的元素的数量（set 的容量）<br>
      –isEmpty()：如果此 set 不包含任何元素，则返回 true<br>
      –contains(E e)：判断是否包含元素<br>
  - TreeSet
    - 构造方法
      –TreeSet name=new TreeSet();<br>
      –import java.util.TreeSet;<br>
      –import java.util.Set;<br>
    - 其他方法
      –add()：将指定的元素添加到此 set(如果该元素尚未存在 set 中)<br>
      –remove(Object o)：将指定的元素从 set 中移除（如果该元素存在于此 set 中）<br>
      –first()：返回此 set 中当前第一个（最低）元素<br>
      –last()：返回此 set 中当前最后一个（最高）元素<br>
      –isEmpty()：如果此 set 不包含任何元素，则返回 true<br>
      –size()：返回 set 中的元素数（set 的容量）<br>
### 集合和数组之间的转换问题
```java
//把集合转换为数组
Object[] objs = set.toArray();
//把数组转换成集合
List<Object> list = Arrays.asList(objs);
```
***
### Queue接口
>队列是一种特殊的线性表，只允许在表的前端(front,队头)进行删除操作，而在表的后端(rear,队尾)进行删除操作<br>
>LinkedList实现了Queue接口
- 方法<br>
   - add(E e): 增加一个元素。成功时返回true，如果队列已满，则抛出一个IIIegaISlabEepeplian异常<br>
   - remove(): 移除并返回队列头部的元素。如果队列为空，则抛出一个NoSuchElementException异常<br>
   - Element(): 返回队列头部的元素。如果队列为空，则抛出一个NoSuchElementException异常<br>
   - offer(E e): 添加一个元素并返回true。如果队列已满，返回false
   - poll(): 移除并返问队列头部的元素。如果队列为空，则返回null<br>
   - peek(): 返回队列头部的元素。如果队列为空，则返回null<br>
   - put(E e): 添加一个元素。如果队列满，则阻塞<br>
   - take(): 移除并返回队列头部的元素。如果队列空，则阻塞<br>
***
# 泛型
## 泛型是把类型明确的工作推迟到创建对象或调用方法的时候才去明确的特殊类型，实现了参数化类型，使代码可以应用于多种类型
***
### 泛型类
- 用法举例<br>
  ```java
  class ObjectExample<T>{   //也可以带有两个类型参数  class ObjectExample<T1, T2>
    private T a;

    public T getA(){

      return a;
    }
    public setA(T a){

      this.obj = a;
    }
  }

  public class Test{
    public static void main(String[] args){

      //现在可以简写成ObjectExample<Integer> a = new ObjectExample<>()
      ObjectExample<Integer> a = new ObjectExample<Integer>();
      a.setA(23);
      System.out.println(a.getA());
    }
  }
  ```
- 容器和泛型关于<>的区别：对于容器而言，<>里放的是容器所装的对象类型，对于泛型来说，<>里放的是类型参数实例化的具体类型，其形式都表现为--xxx <对象类型> mm = new xxx<>();
### 泛型方法
- 用法举例(只需将泛型参数列表置于方法返回值之前即可)
  ```java
  public class GenericMethods{    //普通类

    public <T> void f(T x){   //泛型方法

      System.out.println(x.getClass().getName());
    }
    public static void main(String[] args){
      GenericMethods gm = new GenericMethods();
      gm.<Boolean>f(true);
      gm.<String>f("use generic");
      gm.<Integer>f(3);
    }
  }
  ```
### 受限泛型
> defination:在定义泛型类型时，若没有指定其参数类型继承的类（接口），就默认继承自Objct类，因此在任何类型都可以作为参数传入来实例化该泛型。但如果想要限制使用此泛型的类别，可以在定义参数类型时使用extends关键字指定这个类型的父类（或实现的接口），以确保没有用不适当的类型来实例化类型参数
### 泛型数组
>java本身不嫩创建泛型数组，所以在需要创建数组的地方使用ArrayList
```java
public class Generic <T>{
  private List<T> list = new ArrayList<T>();

  public void add(T item){
    arr.add(item);
  }
  public T get(int index){
    return arr.get(index);
  }
}