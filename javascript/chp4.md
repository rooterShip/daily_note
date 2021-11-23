# 变量、作用域与内存
## 原始值与引用值
***
ECMAScript变量可以包含两种不同类型的数据：原始值和引用值<br>
原始值（primitive value）就是最简单的数据<br>
引用值（reference value）则是由多个值构成的对象<br>
在把一个值赋给变量时，JavaScript引擎必须确定这个值是原始值还是引用值。保存原始值的变量是按值（by value）访问的，但JavaScript不允许直接访问内存位置，因此也就不能直接操作对象所在的内存空间。在操作对象时，实际上操作的是对该对象的**引用**而非对象本身。为此，保存引用值的变量是**按引用**访问的。
- 动态属性<br>
  对于引用值而言，可以随时添加、修改和删除其属性和方法--相对应的原始值无属性
  ```js
  let person = new Object();
  person.name = 'Nicholas';
  console.log(person.name); //"Nicholas"
  ```
  原始类型初始化只可以使用原始字面量形式，如果使用的是new关键字，则javascript会创建 一个Object类型的实例，但其行为类似原始值。
  ```js
  let name1 = 'Nicholas';
  let name2 = new String('Matt');
  name1.age = 27;
  name2.age = 26;
  console.log(name1.age); //undefined
  console.log(name2.age); //26
  console.log(typeof name1); //string
  console.log(typeof name2); //object
- 复制值<br>
  除了存储方式不同，原始值和引用值在通过变量复制时也有所不同。在通过变量把一个原始值赋到另一个变量时，原始值会被复制到新变量的位置。
  ```js
  let num1 = 5;
  let num2 = num1;
  ```
  num1包含数值5，当把num2初始化为null1时，num2也会得到数值5。这个值跟存储在num1中的5是完全独立的。因为它是那个值的副本。
  ![原始值赋值](source_img/原始值赋值.png)
  在把引用值从一个变量赋给另一个变量时，存储在变量中的值也会被复制到新变量所在的位置。区别在于，这里复制的值实际上是一个**指针**，它指向存储在堆内存中的对象。操作完成后，两个变量实际上指向同一个对象，因此一个对象上面的变化会在另一个对象上反映出来。
  ```js
  let obj1 = new Object();
  let obj2 = obj1;
  obj1.name = "Nicholas";
  console.log(obj2.name); //"Nicholas"
  ```
  ![引用值赋值](source_img/引用值赋值.png)
- 传递参数<br>
  ECMAScript中所有函数的参数都是**按值传递**的
  - 原始值：值会被复制到另一个**局部变量**
  - 引用值：值在内存中的**位置**会被保存在另一个**局部变量**（即使对象是按置传进函数的，还是会通过引用访问对象，但始终不是按引用传递），对于这点个人理解为：ECMAScript中函数的参数必定为值传递，当这个参数为引用值时，将该引用值赋给另一个临时的引用值（局部变量），这两个值也同时指向同一个对象，所以一个对对象更改，另一个也会更改。但两者终究不是同一个值即非引用传递，
  ```js
  //原始值为参数
  function addTen(num){
    num += 10;
    return numl
  }

  let count = 20;
  let result = addTen(count);
  console.log(count); //20,没有变化
  console.log(result); //30


  //引用值为参数
  function setName(obj){
    obj.name = 'Nicholas';
  }

  let person = new Object();
  setName(person);
  console.log(person.name); //'Nichols'

  //
  function setName(obj){
    obj.name = 'Nicholas';
    let obj = new Object();
    obj.name = 'Greg';

    let person = new Person();
    setName(person);
    obj.name = 'Greg';
  }
  ```