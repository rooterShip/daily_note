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