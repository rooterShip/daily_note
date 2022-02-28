<!--
 * @Author: Rooter
 * @Date: 2022-02-26 17:10:29
 * @LastEditors: Rooter
 * @LastEditTime: 2022-02-28 22:38:24
-->
# 集合引用类型
## Object
Object是ECMAScript中最常用的类型之一，虽然Object的实例没有多少功能，但**很适合存储和在应用程序间交换数据**。显示的创建Object的实例有两种方式。
- 使用new操作符和Object构造函数来创建Object实例
  ```js
  let person = new Object();
  person.name = "Nicholas";
  person.age = 29;
  ```
- 使用对象字面量（object literal）表示法。对象字面量是对象定义的**简写形式**，目的是为了简化包含大量属性的对象的创建。
  ```js
  let person = {
      name:"Nicholas",
      age:29
  }
  //此处的左大括号表示对象字面量开始，它出现在一个表达式上下文中。（赋值操作符出现表示 表达式上下文
  //如果出现在if后面则为语句上下文
  ```
  对象字面量表示法中，属性名可以是字符串或数值，数值属性会自动转换为字符串。也可以用对象字面量表示法来定义一个只有默认属性和方法的对象，即字面量中大括号内不包含任何东西。
- 点语法与中括号
  属性一般是通过**点语法**来存取的，这也是面向对象语言的惯例，但也可以使用中括号来存取属性，在使用中括号时，要在括号内使用属性名的**字符串形式**
  ```js
  console.log(person["name"]); //Nicholas
  console.log(person.name); //Nicholas -- 注意区分使用中括号时使用了字符串形式
  ```
  中括号的优势：可以通过变量访问属性
  ```js
  let propertyName = "name";
  console.log(person[propertyName]); //Nicholas
  ```
  另外，如果属性名中包含可能会导致语法错误的字符，或者包含关键字/保留字时，也可以使用中括号语法。
  ```js
  person["first name"] = "Nicholas" //first name中包含一个空格，所以不能使用点语法来访问。常规情况下使用点语法来进行属性存取。
  ```
## Array
>ECMAScript数组跟其他变成语言的数组有很大区别：跟其他语言中的数组一样，ECMAScript数组也是一组有序的数据，但跟其他不同的时，数组中每个槽位可以存储任意类型的数据。这意味这可以创建一个数组，它的第一个元素时字符串，第二个元素时数值。且ECMAScript数组也是动态大小的，会随着数据添加而自动增长。
### 创建数组
- 使用Array构造函数
  ```js
  let colors = new Array();
  ```
  如果知道数组中元素的数量那么可以给构造函数传入一个值，然后length属性就会被自动创建并设置为这个值。
  ```js
  let color = new Array(20); //创建一个初始length为20的数组
  ```
- 使用数组字面量