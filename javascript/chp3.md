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
    - 对于对象：
      >调用valueof()方法，并按照上述规则转换返回的值。如果转换结果时NaN,则调用toString()方法，再按照转换字符串的规则转换
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
  - 转换字符串：toString()--返回当前的字符串等价物，toString方法可见于数值、布尔值、对象和字符串值（字符串的toString()方法只是简单返回自身的一个副本）,null和undefined值没有toString()方法<br>
    多数情况下，toString()不接收任何参数。当数值调用该方法时,默认情况下，toString()返回数值的十进制字符串表示，而通过传入参数，可以得到数值的二进制、八进制、十六进制，或者其他任何有效基数的字符串表示。
    ```js
    let num = 10;
    console.log(num.toString()); //"10"
    console.log(num.toString(2)); //"1010"
    console.log(num.toString(8)); //"12"
    console.log(num.toString(10)); //"10"
    console.log(num.toString(16)); //"a"
    ```
    当不确定一个值是不是null或者undefined的时候，可以使用String()转型函数，它始终会返回相应类型值的字符串。String()函数遵循如下规则：
    - 当值又toString()方法，则调用该方法（不传参数）并返回结果
    - 当值是null，返回"null"
    - 当值是undefined，返回"undefined"
    ```js
    let value1 = 10; 
    let value2 = true;
    let value3 = null; 
    let value4;

    console.log(String(value1)); // "10"
    console.log(String(value2)); // "true" 
    console.log(String(value3)); // "null"
    console.log(String(value4)); // "undefined"
    ```
    supplement:
    >用加号操作符给一个值加上一个空字符串""也可以将其转换为字符串
  - 模板字面量：与使用单引号或双引号不同，模板字面量保留换行字符，可以跨行定义字符串<br>
    用模板字面量插入字符串
    ```js
    let value = 5;
    let exponent = 'second';

    //传统插入方法
    let insertRes0 = value + ' to the ' + exponent + ' power is ' + (value * value);

    //模板字面量
    let insertRes1 = `${value} to the ${exponent} power is ${value * value}`;

    //在插值表达式中可以调用函数和方法：
    function capitalize(word){ 
      return `${ word[0].toUpperCase() }${ word.slice(1) }`; 
      }
    console.log(`${ capitalize('hello') }, ${ capitalize('world') }!`); 
    // Hello, World
- Symbol类型(为了解决属性名冲突)
  - 基本用法<br>
    符号需要使用Symbol()函数初始化。且符号本身是**原始类型**
    ```js
    let sym = Symbol();
    console.log(typeof sym); //symbol,typeof的两种用法
    ```
    调用Symbol()函数时，也可以传入一个字符串参数作为对符号的描述（description),将来可通过这个字符来调试代码，但该字符与符号定义或标识完全无关。
    ```js
    let genericSymbol = Symbol();
    let otherGenericSymbol = Symbol();

    let fooSymbol = Symbol('foo');
    let otherFooSymbol = Symbol('foo'); 

    console.log(genericSymbol == otherGenericSymbol); //false
    console.log(fooSymbol == otherFooSymbol); //false 具有两个相同的description值的symbol也是不相等的--没有任何两个symbol是相等的
    ```
    Symbol()函数不能与new关键字一起作为构造函数使用，这样做是为了避免创建符号包装对象。Boolean、String和Number都支持构造函数且可用于初始化包含原始值的包装对象：
    ```js
    let myBoolean = new Boolean();
    console.log(typeof myBoolean); //object

    let myString = new String();
    console.log(typeof myString); //object

    let myNumber = new Number();
    console.log(typeof myNumber); //object

    let mySymbol = new Symbol(); //typeError:Symbol is not a constructor

    //可以采用：
    let mySymbol = Symbol();
    let = myWrappedSymbol = Object(mySymbol);
    conaole.log(typeof myWrappedSymbol); //object
    ```
  - 使用全局符号注册表<br>
    当运行时的不同部分需要共享和重用符号实例时，可以用一个字符串(description)作为键，在全局符号注册表中创建并重用符号。此时要用到Symbol.for()方法：
    ```js
    let fooGlobalSymbol = Symbol.for('foo');
    console.log(typeof foolGlobalSymbol); //symbol
    ```
    Symbol.for()对每个字符串键都执行幂等操作。第一次使用某个字符串调用时，会检查全局运行时注册表，发现不存在对应的符号，于是就会生成一个新符号实例并添加到注册表。后续使用相同字符串的调用同样会检查注册表，发现存在与该字符串相对应的符号，然后就会返回该符号实例
    ```js
    let fooGlobalSymbol = Symbol.for('foo'); //创建新符号
    let otherFoolGlobalSymbol = Symbol.for('foo') //重用已有符号

    console.log(fooGlobalSymbol === otherFoolGlobalSymbol); //true
    ```
    即使采用相同的符号描述，在全局注册表中定义的符号跟使用Symbol()定义的符号也并不等同：
    ```js
    let localSymbol = Symbol('foo');
    let globalSymbol = Symbol.for('foo');

    console.log(localSymbol === globalSymbol); //false
    ```
    全局注册表中的符号必须使用字符串键来创建，因此作为参数传给Symbol.for()的任何值都会被转换为字符串，与此同时，注册表中使用的键同时也会被用作符号描述。
    ```js
    let emptyGlobalSymbol = Symbol.for();
    console.log(emptyGlobalSymbol); //Symbol(undefined)
    ```
    可以使用Symbol.keyFor()来查询**全局注册表**，此方法接收符号，返回该全局符号对应的字符串键。如果查询的不是全局符号，则返回undefined。
    ```js
    //创建全局符号
    let s = Symbol.for('foo');
    console.log(Symbol.keyFor(s)); //foo

    //创建普通符号
    let s2 = Symbol('bar');
    console.log(Symbol.keyFor(s2)); //undefined

    //如果传给Symbol.keyFor()的不是符号，则该方法抛出TypeError：
    Symbol.keyFor(123); //TypeError: 123 is not a symbol
    ```
    应用场景：通常情况下，我们在一个浏览器窗口中（window），使用Symbol()函数来定义和Symbol实例就足够了。但是，如果你的应用涉及到多个window（eg:页面中使用了ifram），并需要这些window中使用的某些Symbol是同一个，我们就需要Symbol.for()来注册全局的Symbol实例：
    ```js
    let gs1 = Symbol.for('globbal_symbol_1') //注册全局symbol
    let gs2 = Symbol.for('globbal_symbol_1') //获取全局Symbol

    gs1 === gs2 //true
    ```
  - 使用Symbol来作为对象属性名（key）（tips：只有字符串和symbol类型才能用作对象属性名）
    ```js
    //用字符串来作属性名
    let obj = {
      "abc" : 123,
      "hello" : "world"
    }

    console.log(obj["abc"]); //123
    console.log(obj["hello"]); //hello

    //用symbol类型来做属性名
    const PROP_NAME = Symbol();
    const PROP_AGE = Symbol();

    let obj = {
      [PROP_NAME] : "ababab",  //注意用symbol变量做属性名的时候要加框
      [PROP_AGE] : "111111",
    }
    ```
    但当Symbol作为对象的属性key后，在对该对象进行key的枚举时，是不能通过Object.keys()或者for...in来枚举,它未被包含在对象自身的属性名集合（property names）之中，所以利用该特性，我们可以把一些不需要对外操作和访问的属性使用Symbol来定义。此外，当使用JSON.stringify()将对象转换成JSON字符串的时候，Symbol属性也会被排除在输出内容之外。
    ```js
    let obj = {
      [Symbol('name')] : 'ahahah',
      age : 18,
      title : 'Engineer'
    }

    Object.keys(obj) //['age', 'title']

    for(let p in obj){
      console.log(p) //分别输出：'age'和'title'
    }

    object.getOwnPropertyNames(obj); //'age','title'

    JSON.stringify(obj); //{"age":18,"title":"engineer}
    ```
    利用针对Symbol的API获取以Symbol方式定义的对象属性：
    ```js
    //使用Object的API
    Object.getOwnPropetySymbols(obj) //[Symbol('name')]

    //使用新增的反射API
    Reflect.ownKeys(obj) //[Symbol('name'), 'age', 'title']
    ```
  - 使用Symbol来替代常量<br>
    通常在代码中定义常量时，是希望这几个常量之间是唯一的关系，为了保证这一点，我们需要为常量赋一个唯一的值，我们可以利用Symbol的唯一性来为常量赋值：
    ```js
    //传统方法
    const TYPE_AUDIO = 'AUDIO'
    const TYPE_VIDEO = 'VIDEO'
    const TYPE_IMAGE = 'IMAGE'

    //symbol
    const TYPE_AUDIO = Symbol()
    const TYPE_VIDEO = Symbol()
    const TYPE_IMAGE = Symbol()
    ```
  - Symbol的内置值
    - Symbol.asyncIterator
      >一个方法，返回对象默认的AsyncIterator,由for-await-of语句使用(实现异步迭代器API的函数)
    - Symbol.hasInstance
      >一个方法，该方法决定一个构造器对象是否认可一个对象是它的实例。由instanceof操作符使用
    - Symbol.isConcatSpredable
      >一个布尔值，如果是true，意味着对象应该用Array.prototype.concat()打平其数组元素。<br>

      ES6中的Array.prototype.concat()方法会根据接收到的对象类型选择如何将一个类数组对象拼接成数组实例。覆盖Symbol.isConcatSpreadable的值可以修改这个行为
      对象的Symbol.isConcatSpreadable属性等于的是一个布尔值，表示该对象用于 Array.prototype.concat()时，是否可以展开
      ```js
      const arr = [1, 2, 3];
      const arr2 = [4, 5, 6];
      arr2[Symbol.isConcatSpreadable] = true;
      console.log(arr.concat(arr2)); //[1, 2, 3, 4, 5, 6]

      arr2[Symbol.isConcatSpreadable] = false;
      console.log(arr.concat(arr2)); //[1, 2, 3, [4, 5, 6]]
      ```
    - Symbol.iterator
      >一个方法，该方法返回对象默认的迭代器。由for-of语句使用（这个符号表示实现迭代器API的函数）
    - Symbol.match
      >一个正则表达式方法，该方法用正则表达式去匹配字符串。由String.prototype.match()方法使用
    - Symbol.replace
      >一个正则表达式方法，该方法返回字符串中匹配正则表达式的索引。由String.prototype.search()方法使用
    - Symbol.split
      >一个正则表达式方法，该方法在匹配正大表达式的索引位置拆分字符串。由String.prototype.split()方法使用。
    - Symbol.toPrimitive
      >一个方法，该方法将对象转换为相应的原始值。由ToPrimitive抽象操作使用
    - Symbol.toStringTag
      >一个字符串，该字符串用于创建对象的默认字符串描述。由内置方法Object.prototype.toString()使用
    - Symbol.unscopables
      >一个对象，该对象所有的以及继承的属性，都会从关联对象的with环境绑定中排除
- Object类型
  - 基本用法<br>
    ECMAScript中的对象其实就是一组数据和功能的集合，开发者可以通过创建Object类型的实例来创建自己的对象，然后再给对象添加属性和方法
    ```js
    let o = new Object();

    let o = new Object; //合法，不推荐
    ```
  - Object实例的属性和方法<br>
    - constructor:用于创建当前对象的函数。
    - hasownProperty(propertyName):用于判断当前对象实例（不是原型）上是否存在给定的属性。要检查的属性名必须是字符串或**符号**（结合前面Symbol）。
    - isPrototypeof(object):用于判断当前对象是否为另一个对象的原型。
    - propertyIsEnumerable(propertyName):用于判断给定的属性是否可以使用for-in语句枚举，同理，属性名必须是字符串。
    - toLocaleString():返回对象的字符串表示，该字符串反应对象所在本地的化执行环境。
    - toString():返回对象的字符串表示。
    - valueOf():返回对象对应的字符串、数值或布尔值表示。通常与toString()的返回值相同。<br>
   由于在ECMAScript中Object是所有对象的基类，所以任何对象都有这些属性和方法。
## 操作符
  >ECMAscript中的操作符是独特的，它们可用于各种值，包括字符串、数值、布尔值，甚至还有**对象**。在应用给对象时，操作符通常会调用valueOf()或toString()方法来取得可以计算的值。
  - 一元操作符
    - 递增/递减操作符（参考C语言）
    - 一元加和减<br>
     如果将一元加应用到非数值，则会执行与使用Number()转型函数一样的类型转换：布尔值false和true转换为0和1，字符串根据特殊规则进行解析，对象调用它们的valueOf()和toString()...
      ```js
      let s1 = "01";
      let s2 = "1.1";
      let s3 = "z";
      let b = false;
      let f = 1.1;
      let o = {
        valueOf(){
          return -1;
        }
      }

      s1 = +s1; // 值变成数值 1 
      s2 = +s2; // 值变成数值 1.1 
      s3 = +s3; // 值变成 NaN 
      b = +b; // 值变成数值 0 
      f = +f; // 不变，还是 1.1
      o = +o; // 值变成数值-1
      ```
      对数值使用一元减会将其变成相应的负值。在应用到非数值时，一元减会遵 循与一元加同样的规则，先对它们进行转换，然后再取负值：
      ```js
      s1 = -s1; // 值变成数值 1 
      s2 = -s2; // 值变成数值 1.1 
      s3 = -s3; // 值变成 NaN 
      b = -b; // 值变成数值 0 
      f = -f; // 不变，还是 1.1
      o = -o; // 值变成数值-1
      ```
    - 位操作符
      - 按位非：~
      - 按位与：&
      - 按位或：|
      - 按位异或：^
      - 左移：<<
      - 有符号右移：>>
      - 无符号右移：>>>
    - 逻辑操作符
      - 逻辑非：！<br>
        逻辑非始终返回布尔值，无论应用到的是什么数据类型--首先将操作数转换为布尔值，然后再对其取反。所以可以用于把任意值转换为布尔值（同时使用两个感叹号）
      - 逻辑与：&&<br>
        逻辑与并不一定会返回布尔值，且遵循短路操作
        - 如果第一个操作数是对象，则返回第二个操作数。
        - 如果第二个操作数是对象，则只有第一个操作数求值为 true 才会返回该对象。
        - 如果两个操作数都是对象，则返回第二个操作数。
        - 如果有一个操作数是 null，则返回 null。
        - 如果有一个操作数是 NaN，则返回 NaN。
        - 如果有一个操作数是 undefined，则返回 undefined。
      - 逻辑或：||<br>
          逻辑或并不一定会返回布尔值，且遵循短路操作
        - 如果第一个操作数是对象，则返回第一个操作数。
        - 如果第一个操作数求值为 false 则会返回第二个操作数。
        - 如果两个操作数都是对象，则返回第一个操作数。
        - 如果有两个操作数是 null，则返回 null。
        - 如果有两个操作数是 NaN，则返回 NaN。
        - 如果有两个操作数是 undefined，则返回 undefined。
    - 指数操作符<br>
      - 指数操作符：**（同python）
      - 指数赋值操作符：**=
        ```js
        let squared = 3;
        squraed **= 2; //9
        ```
## 语句
- if语句（条件不一定要布尔值，可以转化）
- do-while语句
- while语句
- for语句
- for-in语句<br>
  for-in语句是一种严格的迭代语句，用于枚举对象中的**非符号键属性**
  ```js
  for(property in expression){
    statement
  }
  ```
- for-of语句<br>
   ```js
  for(property of expression){
    statement
  }
  ```
- 标签语句<br>
  标签语句用于给语句加标签
  ```js
  label : statement

  //start是一个标签，可以在后面通过break或continue语句引用，标签语句的典型应用场景是嵌套循环
  start: for(let i = 0; i < count; i++){
    console.log(i);
  }
  ```
- break和continue语句<br>
  break和continue可以与标签语句一起使用，返回代码中特定的位置。
  ```js
  let num = 0;

  outermost:
  for(let i = 0; i < 10; i++){
    for(let j = 0; j < 10; j++){
      if(i == 5 && j == 5){
        break outermost; //本应退出内循环，现在强制退出到标签处，continue同理
      }
      num++;
    }
  }

  console.log(num); //55
  ```
- with语句<br>(不推荐使用)
  with语句的用途是将代码作用域设置为特定的对象
  ```js
  with (expression) statement;
  ```
- switch语句（在比较每个条件值时会使用全等操作符，不会强制转换数据类型，即10！="10"）