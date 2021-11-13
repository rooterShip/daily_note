# 语言基础
## 语法
***
- 区分大小写
- 标识符：
  - 第一个字符必须是一个字母、下划线、**美元符号（$）**
  - 剩下的其他字符可以是字母、下划线、美元符号或数字
  - 命名方式：驼峰命名法
- 注释：采用C语言风格的注释
- 严格模式（strict mode）<br>
  严格模式是一种不同的的JavaScript解析和执行模型，ECMAScript3的一些不规范写法在这种模式下会被处理，对于不安全的活动将抛出错误。要对整个脚本启用严格模式需在脚本开头加上：
  ```js
  "use strict";
  ```
  也可单独指定一个函数在严格模式下执行
  ```js
  function doSomething(){
      "use strict";
  }
  ```
- 语句<br>
  ECMAScipt中的语句以分号结尾，省略分号意味着由解析器确定语句在哪里结尾
  ```js
  let sum = a + b //没有分号也有效，不推荐
  let diff = a - b;
  ```
## 变量
***
ECMAScript变量是松散类型的--变量可以用于保存任何类型的数据。每个变量不过是一个用于保存任意值的命名占位符。声明变量的关键字：var、const、let。（const和let只能在ECMAScript6及更晚的版本中使用）
- var
  - 定义变量
  ```js
  var message; //可以保存任何类型的值（不初始化，变量保存特殊值undefined
  ```
  - 定义变量并初始化
  ```js
  var message = "hi"; //不会将变量标识为字符串
  message = 100;      //合法，不推荐
  ```
  - var声明作用域<br>
  在函数中（局部）使用var定义变量时为局部变量，即变量会随着函数的退出被销毁<br>
  当省略var后，变量就会变成全局变量（不推荐，难以维护）
  - var声明提升(hoist)
  ```js
  function foo(){
      console.log(age);
      var age = 26;
  }
  ```
  此段代码在执行到console.log(age)的时候不会因为age没有被定义而报错，由于声明提升，等价于：
  ```js
  function() foo{
      var age;
      console.log(age);
      age = 26;
  }
  ```
  - 使用var可以多次声明同一个变量
  ```js
  function foo(){
      var age = 16;
      var age = 26;
      console.log(age) //26
  }
  ```
