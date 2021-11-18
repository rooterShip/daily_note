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
  - 在定义将来要保存对象值的变量时，建议使用null来初始化。因此，当判断一个变量是否在后来被重新赋予了一个对象的引用时，只要检查这个变量的值是不是null。
    ```js
    if(car != null){
      // car是一个对象的引用
    }
    ```
  - undefined和null的关系--undefined的值是由null值派生而来的，因此有：
    ```js
    console.log(null == undefined); //true
    ```
    不同于undefined，永远不必显示地将变量设置为undefined，任何时候，只要变量要保存对象，而当时又没有对象可保存，就要用null来填充变量。
- Boolean类型
  - 布尔值与不同类型之间的转换：<br>
    | 数据类型 | 转换为ture的值 | 转换为false的值 |
    | :----: | :----: | :----: |
    | Boolean | true | false |
    | string | 非空字符串 | ""(空字符串)|
    | Number | 非零数值（包括无穷值） | 0、NaN |
    | Object | 任意对象 | null |
    | Undefined | N/A(不存在) | undefined |
  - 流控制语句会自动执行其他类型值到布尔值的转换
    ```js
    let message = "Hello world";
    if(message){ //将字符串自动转化为Boolean类型
      console.log("Value is true");
    }
    ```
- NaN <br>
  - NaN -- Not a Number（不是数值），用于表示本来要返回数值的操作失败了（不是抛出错误）
    ```js
    console.log(0/0);   //NaN
    console.log(-0/+0); //NaN
    ```
    ```js
    console.log(5/0); //Infinity(正无穷大)
    console.log(5/-0); //-Infinity(负无穷大)
    ```
  - NaN的独特属性
    - 任何涉及NaN的操作始终返回NaN(连续多步计算时需要格外关注)
      ```js
      console.log(NaN/10); //NaN
    - NaN不等于包括NaN在内的任何值
      ```js
      console.log(NaN == NaN); //false
      ```
  - isNaN()函数（判断参数是否“不是数值”）
    - 该函数会尝试把不是数值的参数转化为数值，任何不能转换为数值的值都会导致这个函数返回true
      ```js
      console.log(isNaN(NaN)) //ture
      console.log(isNaN(10)); //false,10为数值
      console.log(isNaN("10")); //false, 可以转化为数值10
      console.log(isNaN("blue")); //true,不可以转换为数值
      console.log(isNaN(true)); //false,可以转换为数值1
      ```
- 数值转换
  - Number()--转型函数，可用于任何数据类型<br>
    - 对于布尔值，ture转换为1，false转换为0
    - 对于数值，直接返回
    - null，返回0
    - undefined，返回NaN
    - 对于字符串：
      >若字符串包含数值字符，包括数值字符前面带加、减号的情况，则转换为一个十进制数值<br>
      ```js
      console.log(Number("1"))   //1
      console.log(Number("123")) //123
      console.log(Number("011")) //11
      ```
      >若字符串包含有效的浮点值格式，则会转换为相应的浮点值（规则如上）<br>
      >若字符串包含有效的十六进制格式如“0xf",则会转换为十六进制对应的十进制整数值<br>
      >若为空字符串（不包含字符），返回0
      >若字符v换包含除上述情况之外的其他字符，返回NaN
    - 对于对象：调用valueof()方法，并按照上述规则转换返回的值。如果转换结果时NaN,则调用toString()方法，再按照转换字符串的规则转换
  - parseInt()--主要用于将字符串转换为数值<br>
    规则如下：
    ```js
    let num1 = parseInt("1234blue"); //1234
    let num2 = parseInt(""); //NaN -- 注意区别于Number函数的类型转换
    let num3 = parseInt("0xA"); //10
    let num4 = parseInt("22.5"); //22
    let num5 = parseInt("70"); //70
    let num6 = parseInt("0xf"); //15
    let num7 = pasrseInt("0xAF", 16); //175，第二个值为指定底数（进制数）

    let num7 = pasrseInt("AF", 16); //175
    let num7 = pasrseInt("AF"); //NaN
    ```
  - parseFloat()--主要用于将字符串转换为数值<br>
    规则如下：
    ```js
    let num1 = parseFloat("1234blue"); // 1234，按整数解析 
    let num2 = parseFloat("0xA"); //0
    let num3 = parseFloat("22.5"); //22.5
    let num4 = parseFloat("22.34.5"); // 22.34 
    let num5 = parseFloat("0908.5"); // 908.5
    let num6 = parseFloat("3.125e7"); // 31250000
    ```
- String类型
  - String（字符串）数据类型表示零或多个16位Unicode字符序列。字符串可以使用双引号（"）、单引号（'）、或反引号（`）标示：
    ```js
    let firstname = "John";
    let lastname = 'jacob'
    let lastname = `Jingle`
    ```
    和c++等语言不同，ECMAScript语法中不同的引号不会改变对字符串的解释
  - 字符字面量<br>
    | 字面量 | 含义 |
    | :----: | :----: |
    | \n | 换行 |
    | \t | 制表 |
    | \b | 退格 |
    | \r | 回车 |
    | \f | 换页 |
    | \\\ | 反斜杠(\\) |
    | \\" | 双引号（"）|
    | \\` | 反引号（`）|
    | \\' |单引号（'），在字符串以单引号标示时使用，例如'He said, \'hey.\''|
    | \\xnn | 以十六进制编码 nn 表示的字符（其中 n 是十六进制数字 0~F），例如\x41 等于"A"|
    | \\unnnn | 以十六进制编码 nnnn 表示的 Unicode字符（其中 n 是十六进制数字 0~F），例如\u03a3 等于希腊字 符"Σ"|

    字符面量可以出现在字符串中的任意位置 ，且可以作为**单个字符**被解释：
    ```js
    let text = "This is the letter sigma: \u03a3.";

    console.log(text.length); //28，这里转义序列表示一个字符
    ```
  - ECMAScript中的字符串是不可变的（immutable），即字符串一旦被创建，其值就不可被改变。要修改某个变量中的字符串值，必须先销毁原始的字符串，然后将包含新值的另一个字符串保存到该变量：
    ```js
    let lang = "java";

    lang = lang + "Script"
    ```
    在本例中，变量lang中一开始包含字符串"java"。当lang被重新定义为包含"java"和"script"的组合--"javascript"。整个过程首先会分配一个足够容纳10个字符的空间，然后填充上"java"和"script"。最后**销毁原始的字符串**"java"和字符串"Script"
    