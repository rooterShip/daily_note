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
    var message; //可以保存任何类型的值（不初始化，变量保存特殊值undefined）
    ```
  - 定义变量并初始化
    ```js
    var message = "hi"; //不会将变量标识为字符串(存疑)
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
- let声明
  - let与var的区别:let声明的范围是**块作用域**，var声明的范围是**函数作用域**
    ```js
    if(ture){
      var name = 'Matt';
      console.log(name); //Matt
    }
    console.log(name); //Matt

    if(ture){
      let age = 26;
      console.log(age); //26
    }
    console.log(age); //无定义
    ```
    age变量不能再if块外部被引用--let作用域仅限于该块内部。块作用域是函数作用域的子集，即var的作用域限制同样适用于let。
  - let声明不允许同一个块作用域中出现冗余声明(不在同一个块中可以)
    ```js
    var name;
    var name;

    let age;
    let age; //SyntaxError;标识符age已经声明过了

    var age;
    let age; //SyntaxError;标识符age已经声明过了

    let age;
    var age; //SyntaxError;标识符age已经声明过了
    ```
  - let声明的变量不会在作用域中被提升(暂时性死区)
    ```js
    console.log(age); // age无定义
    let age = 26;
    ```
  - 全局声明<br>
    使用let在全局作用域中声明的变量不会成为window对象的属性(var声明的变量则会)
    ```js
    var name = 'Matt';
    console.log(window.name); //'Matt'

    let age = 26;
    console.log(window.age); //undefined
    ```
  - for循环中的let声明<br>
    for循环中用var声明变量的时候，for循环定义的迭代变量会渗透到循环体外部：
    ```js
    for(var i = 0; i < 5; i++){
      //循环逻辑
    }
    console.log(i); //5
    ```
    for循环中用let声明变量的时候，for循环定义的迭代变量不会渗透到循环体外部：
    ```js
    for(let i = 0; i < 5; i++){
      //循环逻辑
    }
    console.log(i) //无定义
    ```
- const声明<br>
  - const行为与let基本相同，区别在于用const声明变量时必须同时初始化变量，并且尝试修改const声明的变量会导致运行错误。
    ```js
    const age = 26;
    age = 36; //TypeError:给常量赋值

    //const不允许重复声明
    const name = 'Matt';
    const name = 'Nicholas';

    //const声明的作用域为块
    const name = 'Matt';
    if(ture)
    {
      const name = 'Nicholas';
    }
    console.log(name); //Matt
    ```
  - const声明的限制只适用于它指向变量的引用，当const变量引用的为对象时，修改这个对象内部的属性并不违反const的限制，eg:
    ```js
    const person = {};
    person.name = 'Matt'; //ok
    ```
  - 不能用const来声明**迭代变量**
    ```js
    for(const i = 0; i < 10; i++) {} //TypeErroe:给常量赋值
    ```
- 最佳命名风格实践
  - 不使用var
  - const优先，let次之（提前知道未来会有修改时）
## 数据类型
***
- typeof操作符（判断标识符类型）
  - undefined&emsp;&ensp;&nbsp;值未定义
  - boolean&emsp;&emsp;&ensp; 值为布尔值
  - string&emsp;&emsp;&emsp;&ensp; 值为字符串
  - number&emsp;&emsp;&ensp;&nbsp;值为数值
  - object&emsp;&emsp;&emsp;&ensp;值为对象或null
  - function&emsp;&emsp;&ensp;值为函数
  - symbol&emsp;&emsp;&emsp;值为符号
- undefined类型<br>
  使用var或let声明了变量但没有初始化时 => 给变量赋予了undefined值
  - 当变量未得到声明时，值不为undefined
    ```js
    let message;

    //无 leg age;
    console.log(message); //'undefined'
    console.log(age); //报错
    ```
  - 当变量未得到声明或者未初始化时，其typeof后都为undefined
    ```js
    let message;

    //无 let age;
    console.log(typeof(message)); //'undefined'
    console.log(typeof(age)); //'undefined'
    ```
- Null<br>
  - Null类型包含唯一值--特殊值null，逻辑上讲，null表示一个空**对象指针**
    ```js
    let car = null;
    console.log(typeof car); //"object"
    ```
  - 