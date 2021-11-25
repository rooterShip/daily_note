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
- 确定类型<br>
  typeof操作符适合用来判断一个变量是否为原始类型，当一个变量是引用类型的时候，typeof只能返回object，并不能返回具体的object。<br>
  ECMAScript提供了instacnceof操作符，语法如下：
  ```js
  result = variable instanceof constructor;

  console.log(person instanceof Object); //变量person是Object吗？ true or false
  console.lof(colors instanceof Array); //变量colors是Array吗？ true or false
  console.lof(pattern instanceof RegExp); //变量Pattern是RegExp吗？ true or false
  ```
## 执行上下文与作用域<br>
***
变量或函数的执行上下文决定了它们可以访问哪些数据，以及它们的行为。每个上下文都有一个关联的**变量对象（variable object)**，而这个上下文中定义的所有变量和函数都存在于这个对象上。虽然无法通过代码访问变量，但后台处理数据会用到它。
- 全局上下文<br>
  全局上下文是最外层的上下文。根据ECMAScript实现的宿主环境，表示全局上下文的对象可能不一样。在浏览器中，全局上下文就是我们常说的window对象，因此通过var定义的全局变量和函数都会成为window对象的属性和方法。使用let和const的顶级声明不会定义在全局上下文中，但在作用域链解析上效果是一样的。上下文在其所有代码都执行完毕后会被销毁，包括定义在它上面的所有变量和函数。<br>
  每个函数调用都有自己的（执行）上下文。当代码执行流进入函数时，函数的上下文被推到一个上下文栈上（栈顶）。在函数执行完之后，上下文栈会弹出该函数上下文，将控制权返还给之前的执行上下文。ECMAScript程序的执行流程就是通过这个上下文栈进行控制的。<br>
  上下文中的代码在执行的时候，会创建变量对象的一个**作用域链**（scope chain），这个作用域链决定了各级上下文中的代码在访问变量和函数时的顺序。代码正在执行的上下文的变量对象始终位于作用域的最前端。如果上下文是函数，则其**活动对象（activation object）**用作变量对象。活动对象最初只有一个定义变量：arguments。（全局上下文中没有这个变量）作用域链中的下一个变量对象来自包含上下文，再下一个对象来自再下一个包含上下文。以此类推直至全局上下文；全局上下文的变量对象始终是作用域链的最后一个变量对象。