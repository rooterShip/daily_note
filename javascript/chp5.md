# 基本引用类型
之前提到过的引用值（或者对象）是某个特定**引用类型**的实例，在EMAScript中，引用类型是把数据和功能组织到一起的结构，经常被人错误地称作“类”。尽管javascript从技术上讲是一门面向对象语言，但ECMAScript缺少传统的面向对象编程语言所具备的基本结构，包括**类和接口**。引用类型有时候也被称为**对象定义**
对象被认为是某个特定引用类型的**实例**。新对象通过使用new操作符后跟一个**构造函数**来创建。构造函数就是用来创建新对象的函数
```js
//创建引用类型Date的新实例，并将它保存在变量now中。Date()在这里就是构造函数
let now = new Date();
```
## Date
***
ECMAScript的Date类型参考了java早期版本中的java.util.Date。为此，Date类型将日期保存为自协调世界时（UTC,Universal Time Coordinated）时间1970年1月1日午夜（零时）至今所经过的毫秒数。<br>
创建日期对象：
```js
let now = new Date();
```
在不给Date构造函数传参数的情况下，创建的对象将保存当前日期和时间。要基于其他日期和时间创建日期对象，必须传入其毫秒表示（UNIX纪元1970年1月1日午夜之后的毫秒数）<br>
辅助方法：<br>
**1.Date.parse()**<br>
接收一个表示日期的字符串参数，尝试将这个字符串转换为表示该日期的毫秒数所支持日期格式有：
- 月/日/年，如"5/23/2019";
- 月名 日，年，如"May 23,2019";
- 周几 月名 日 年 时：分：秒：时区，如"Tue May 23 2019 00:00:00 GMT-0700";
```js
let someDate = new Date(Date.parse("May 23,2019"));
```
如果传给Date.parse()的字符串并不表示日期，则该方法会返回NAN。如果直接把日期的字符串传给Date构造函数，那么Date会在后台调用Date.parse()
```js
let someDate = new Date("May 23,2019");
```
**2.Date.UTC()**<br>
返回日期的毫秒，传入的参数是年、零起点月数、日、时、分、秒、毫秒，这些参数只有前两个（年和月）是必需的。如果不提供日，那么默认为1日。其他参数的默认值都是0。
```js
//2000年1月1日零点
let y2k = new Date(Date.UTC(2000,0));
```
- 继承的方法<br>
  Date类型重写了toLocalString()、toString()和valueOf()方法。toLocalString()方法返回与浏览器运行的本地环境一致的日期和时间，不包含时区信息。toString()方法通常返回带时区信息的日期和时间。valueOf()方法返回日期的毫秒表示
  ```js
  toLocalStrig() - 2/1/2019 12:00:00 AM
  toString() -Thu Feb 1 2019 00:00:00 GMT-0800

  let date1 = new Date(2019,0,1);
  let date2 = new Date(2019,1,1);
  console.log(date1<date2); //true
  console.log(date1>date2); //false
  ```
- 日期格式化方法
  -  toDateString()显示日期中的周几、月、日、年（格式特定于实现）； 
  -  toTimeString()显示日期中的时、分、秒和时区（格式特定于实现）
  -  toLocaleDateString()显示日期中的周几、月、日、年（格式特定于实现和地区）； 
  -  toLocaleTimeString()显示日期中的时、分、秒（格式特定于实现和地区）；
  -  toUTCString()显示完整的 UTC日期（格式特定于实现）。
## RegExp
***
```js
let expression = /pattern/flags;
```
正则表达式的pattern（模式）可以是任何简单或复杂的正则表达式，包括字符类、限定符、分组、向前查找和反向引用。每个正则表达式可以带零个或多个flags(标记)，用于控制正则表达式的行为，表示匹配模式的标记有：
- g:全局模式，表示查找字符串的全部内容，而不是找到第一个匹配的内容就结束。
- i:不区分大小写，表示在查找匹配时忽略pattern和字符串的大小写。
- m:多行模式，表示查找到一行文本末尾时会继续查找。
- y:粘附模式，表示只查找从lastIndex开始及之后的字符串。
- u:Unicode模式，启用Unicode匹配。
- s:dotAll模式，表示元字符，匹配任何字符（包括\n或\r）。
```js
//匹配字符串中的所有"at"
let pattern1 = /at/g;

//匹配第一个"bat"或"cat"，忽略大小写
let pattern2 = /[bc]at/i;

//匹配以"at"结尾的三字符组合，忽略大小写
let pattern3 = /.at/gi;
```
由于元字符在正则表达式中都有一种或多种特殊功能，所以元字符必须转义（同其他语言），包括：{ [ \ ^ $ | ? * + .
```js
//匹配第一个"[bc]at"，忽略大小写
let pattern1 = /\[bc\]at/i;
```
还可以通过使用RegExp构造函数来创建，接收两个参数：模式字符串和（可选的）标记字符串。
```js
//匹配第一个"bat"或"cat"，忽略大小写
let pattern2 = /[bc]at/i;

// 跟 pattern1 一样，只不过是用构造函数创建的 
let pattern2 = new RegExp("[bc]at", "i");
```
## 原始值包装类型
为了操作原始值，ECMAScript提供了3种特殊的引用类型：Boolean、Number和String。每当用到某个原始值的方法或属性时，后台都会创建一个相应原始包装类型的对象，从而暴露出操作原始值的各种方法。
```js
let s1 = "some text"
let s2 = s1.substring(2);
```
s1是一个包含字符串的变量，是一个原始值（没有方法）。之所以能够调用substring方法，是因为在后台进行了很多处理。当第二行访问s1时，是以读模式访问（从内存中读取变量保存的值），在以读模式访问字符串值的任何时候，后台会执行以下3步：<br>
(1)创建一个String类型的实例<br>
(2)调用实例上的特定方法<br>
(3)销毁实例<br>
```js
let s1 = new String("some text");
let s2 = s1.substring(2);
s1 = null;
```
这种行为让原始值拥有对象的行为，布尔值和数值同理。<br>
引用类型与原始包装类型的主要区别在于对象的生命周期。在通过new实例化引用类型后，得到的实例会在离开作用域时被销毁，而自动创建的原始值包装对象则只存在于访问它的那行代码执行期间。这意味着不能在运行时给原始值添加属性和方法。
```js
//第二行代码运行时会临时创建一个string对象，但是在赋值完后，这个对象已经被销毁了，所以第三行代码执行时会为undefined，实质上，第三行代码在调用原始值属性时，是创建了自己的string对象，只不过该对象不含有color属性
let s1 = "some text";
s1.color = "red";
console.log(s1.color); //undefined
```
Object构造函数作为一个工厂方法，能够根据传入值的类型返回相应原始值包装类型的实例
```js
let obj = new Object("some text");
console.log(obj instanceof String); //true
```
使用new调用原始值包装类型的构造函数，与调用同名的转型函数并不一样
```js
let value = "25";
let number = Number(value); //转型函数
console.log(typeof number); //"number"
let obj = new Number(value); //构造函数
console.log(typeof obj); //"object"
```
### Boolean
Boolean是对应布尔值的引用类型。要创建一个Boolean对象，就使用Boolean构造函数并传入true或false
```js
let booleanObject = new Boolean(true);
```
所有对象在布尔表达式中都会自动转换为true
```js
let falseObject = new Boolean(false);
let result = falseObject && true;
console.log(result); //true-- 所有对象在布尔表达式中都会自动转换为true

let falseValue = false;
result = falseValue && true;
console.log(result); //false
```
Boolean的实例会重写valueOf()方法，返回一个原始值true或false。toString()方法被调用时也会被覆盖，返回字符串"true"或"false"。
### Number
Number是对应数值的引用类型。要创建一个Number对象，就使用Number构造函数并传入一个数值。
```js
let numberObject = new Number(123);
```
与Boolean一样，Number类型重写了valueOf()、toLocalString()和toString()方法。valueOf方法返回Number对象的原始数值，另外两个方法返回会数值字符串。toString()方法可选地接收一个表示基数的参数，并返回相应基数形式的数值字符串
```js
let num = 10;
console.log(num.toString()); //"10"
console.log(num.toString(2)); //"1010"
console.log(num.toString(8)); //"12"
console.log(num.toString(10)); //"10"
console.log(num.toString(16)); //"a"
```
除了继承的方法，Number类型还提供了几个用于将数值格式化为字符串的方法。<br>
- toFixed()方法返回包含指定小数点位数的数值字符串
  ```js
  let num = 10;
  console.log(num.toFixed(2)); //"10.00"
  //第二行代码就是隐含创建了对象并在结束时销毁了实例
  let num = 10.005 //假设与上一段代码不在同一个块内，同一个块内let声明不允许存在冗余
  console.log(num.toFixed(2)); //"10.01"
  ```
- toExponential()方法返回以科学计数法
  ```js
  let num = 10;
  console.log(num.toExponential(1)); //"1.0e+1"
  ```
- toPrecision()方法会根据情况返回最合理的输出结果，可能是固定长度，也可能是科学计数法形式。
  ```js
  let num = 99;
  console.log(num.toPrecision(1)); //"1e+2"
  console.log(num.toPrecision(2)); //"99"
  console.log(num.toPrecision(3)); //"99.0"
  ```
### String
String是对应字符串的引用类型。要创建一个String对象，使用String构造函数并传入一个数值
```js
let stringObject = new String("hello world")
```
String对象的方法可以在所有字符串原始值上调用。3个继承方法valueOf()、toLocalString()和toString()都返回对象的原始字符串值。<br>
每个String对象都有一个length属性，表示字符串中字符的数量。
```js
let stringValue = "hello word";
console.log(stringValue.length);//11 
// 即使字符串中包含双字节字符（而不是单字节的ASCII字符），也仍然会按单字符来计数。
```
- JavaScript字符
  JavaScript字符串由16位码元（code unit）组成。对多数字符来说，每16位码元对应一个字符
  ```js
  ler message = "abcde";
  console.log(message.length); //5
  ```
  此外，charAt()方法返回给定索引位置的字符，由传给方法的整数参数指定。具体来说，这个方法查找指定索引位置的16位码元，并返回该码元对应的字符：
  ```js
  let message = "abcde"
  console.log(message.charAt(2)); //"c"
  ```
- 字符串操作方法
  - concat()(相当于“+”操作)
    ```js
    let stringValue = "hello ";
    let result = stringValue.concat("world");

    console.log(result); //"hello world"
    console.log(stringValue); //"hello"
    ```
    concat()方法可以接收任意多个参数，因此可以一次性拼接多个字符串。
  - slice()、substr()和substring()<br>
  返回调用它们的字符串的一个子字符串，并且都接收一个或两个参数，第一个参数表示子字符串开始的位置，第二个参数表示子字符串结束的位置<br>
    ```js
    //参数为正时
    let stringValue = "hello world";
    console.log(stringValue.slice(3)); //"lo world"---省略第二个参数表示提取到字符串末尾
    console.log(stringValue.substring(3)); //"lo world"
    console.log(stringValue.substr(3)); //"lo world"
    console.log(stringValue.slice(3,7)); //"lo w"
    console.log(stringValue.substring(3,7)); //"lo w"--对于slice、substring而言，第二个参数表示结束时的位置
    console.log(stringValue.substr(3,7)); //"lo world"--对于substr而言，第二个参数表示字串中包含的字符个数

    //参数为负时
    let stringValue = "hello world";
    console.log(stringValue.slice(-3)); //"rld"
    console.log(stringValue.substring(-3)); //hello world"
    console.log(stringValue.substr(-3)); //"rld"
    console.log(stringValue.slice(3,-4)); //"lo w
    console.log(stringValue.substring(3, -4)); //"hel"
    console.log(stringValue.substr(3,-4)); //""(empty string)
    //slice()参数将所有负参数值加上字符串长度得到正值，按照之前正值规则进行计算
    //substr()方法将第一个负参数值加上字符串长度得到成正值，按照之前正值的规则进行计算，将第二个负参数值转换为0
    //substring()方法将所有负值参数都转换为0
    ```
  - indexOf()和lastIndexOf()--字符串位置方法<br>
    这两个方法从字符串中搜索传入的字符串，并返回位置（如果没找到，则返回-1），两者的区别在于，indexOf()方法从字符串开头开始查找字符串，而lastIndexOf()方法从字符串末尾开始查找字符串。
    ```js
    let stringValue = "hello world";
    console.log(stringValue.indexOf("o")); //4
    console.log(stringValue.indexof("o")); //7


    //当这两个函数接收第二个参数时表示从第二个参数表示的位置开始查找
    console.log(stringValue.indexOf("o",6)); //7
    console.log(stringValue.indexof("o",6)); //4
    ```
  - startsWith()、endsWith()、includes()--字符串包含方法<br>
    startsWith()检查开始于索引0的匹配项，endsWith检查开始于索引(string.length - substring.length)的匹配项，includes()检查整个字符串
    ```js
    let message = "foobarbaz";
    console.log(message.startsWith("foo")); //true
    console.log(message.startsWith("bar")); //false
    
    console.log(message.endsWith("baz")); //false
    console.log(message.endsWith("bar")); //true

    console.log(message.includes("baz")); //true
    console.log(message.includes("qux")); //false
    ```
    startsWith()和includes()方法接收可选的第二个参数，表示开始搜索的位置。如果传入第二个参数，则意味着这连个方法会从指定位置向着字符串末尾搜索，忽略该位置之前的所有字符。
    ```js
    let message = "foobarbaz";

    console.log(message.startsWith("foo")); //true
    console.log(message.startsWith("foo",1)); //false

    console.log(message.includes("bar")); //true
    console.log(message.includes("bar",4)); //false
    ```
    endsWith()方法接收可选的第二个参数，表示应该当作字符串末尾的位置。
    ```js
    let message = "foobarbaz";

    console.log(message.endsWith("bar")); //false
    console.log(message.endsWith("bar",6)); //true
    ```
  - trim()--去除前后所有空格符，再返回结果<br>
    这个方法创建字符串的一个**副本**
    ```js
    let stringValue = "  hello world  ";
    let trimmedStringValue = stringValue.trim();
    console.log(stringValue); //"  hello world  "
    console.log(trimmedStringValue); //"hello world"
    ```
    **由于trim()返回的是字符串的副本，因此原始字符串不受影响，即原本的前、后空格符都会保留。** 另外，trimLeft()和trimRight()方法分别用于从字符串开始和末尾清理空格符。
  - repeat()方法<br>
    ECMAScript在所有字符串上都提供了repeat()方法。这个方法接收一个整数参数，表示要将字符串复制多少次，然后返回拼接所有副本后的结果。
    ```js
    let stringValue = "na";
    console.log(stringValue.repeat(16) + "batman");
    // nanananananananananananananananabatman
    ```
  - padStart()和padEnd()方法<br>
    padStart()和padEnd()方法会复制字符串，如果小于指定长度，则在相应一边填充字符，直至满足长度条件。这两个方法的第一个参数是长度，第二个参数是可选的填充字符串，默认为空格
    ```js
    let stringValue = "foo";

    console.log(stringValue.padStart(6)); //"   foo"
    console.log(stringValue.padStart(9,".")); //"......foo"

    console.log(stringValue.padEnd(6)); //"foo   "
    console.log(stringValue.padEnd(9,".")); //"foo......"
    ```
    可选的第二个参数并不限于一个字符。如果提供了多个字符的字符串，则会将其拼接并截断以匹配指定长度。此外，如果长度小于或等于字符串长度，则会返回原始字符串。
    ```js
    let stringValue = "foo";
    console.log(stringValue.padStart(8,"bar")); //"barbafoo"
    console.log(stringValue.padStart(2)); //"foo"

    console.log(stringValue.padEnd(8,"bar")) //"foobarba"
    console.log(stringValue.padEnd(2)) //"foo
    ```
   - 字符串迭代与解构<br>
     - iteration<br>
       iteration为迭代器（遍历器），其作用为给不同的数据结构提供统一的遍历访问机制，这种机制可以使得数据结构里的成员按照顺序依次被遍历访问，常见的有数组、map的遍历
       ```js
       const arr = ['a','b','c'];
       for(let i of arr){
         console.log(i); //a b c
       } 

       const map = new map[[1,'x'],[2,'y'],[3,'z']];
       for(let j of map){
         console.log(j); //[1,'x'],[2,'y'],[3,'z']
       }
       ```
       for-of循环的背后调用iteration，由于数组对象上部署了iteration属性，数组是一个可迭代对象，因此数组可以被for-of遍历
      - iteration工作原理<br>
        iteration遍历的时候会先生成一个**指针对象**，指向当前数据结构的起始位置（即iteration本质上就是指针对象），这个对象里有一个next方法，然后调用该**next**方法，移动指针使得指针指向数据结构中的第一个元素，每调用一次next方法，指针就指向数据结构里的下一个元素，不断的调用next方法实现遍历元素的效果。另外每一次调用next方法，都会返回数据结构的当前成员的信息，具体来说，就是返回一个包含**value**和**done**两个属性的对象，其中value属性是当前成员的值，done属性是一个布尔值，表示遍历是否结束，没有结束返回false，结束返回为true。
      - 原生及自定义的iterator接口<br>
        在javascript中原生具备iterator接口的数据结构有：<br>
        - Array
        - Map
        - Set
        - String
        - TypedArray
        - 函数的arguments对象
        - NodeList对象、
        
        当我们使用for-of循环遍历这些数据结构时，该循环会自动去寻找iterator接口并执行遍历操作，一种数据结构只要部署了Iterator接口，我们就称这种数据结构为“可遍历的（iterable）”<br>
        ES6规定，默认的iterator接口部署在数据结构的Symbol.iterator属性，Symbol.iterator属性本身是一个函数，就是当前数据结构默认的遍历器生成函数，执行这个函数，就会返回一个带有next方法的遍历器对象。属性名Symbol.iterator是一个表达式，返回Symbol对象的iterator属性，这是一个预定义好的、类型为Symbol的特殊值，所以要放在**方括号**内。
      - 字符串手动迭代
        ```js
        let message = "abc";
        let stringIterator = message[Symbol.iterator]();
        // stringIterator为遍历器对象
        console.log(stringIterator.next()); //{value:"a",done:false}
        console.log(stringIterator.next()); //{value:"b",done:false}
        console.log(stringIterator.next()); //{value:"c",done:false}
        console.log(stringIterator.next()); //{value:"undefined",done:trur}
        ```
      - 在for-of循环中可以通过迭代器访问每个字符
        ```js
        for(const c of "abcde"){  //普通迭代不能使用const声明
          console.log(c);
        } // a b c d e
        ```
      - 解构字符串
        ```js
        let message = "abcde";
        console.log([...message]); //["a","b","c","d","e"]
        ```
  - toLowerCase()、toLocaleLowerCase()、toUpperCase()、toLocaleUpperCase()--字符串大小写转换<br>
    toLowerCase()和toLocaleCase()方法同java.lang.String中的方法。toLocaleLowerCase()和toLocaleUpperCase()方法旨在于基于特定地区实现。在很多地区，地区特定的方法与通用的方法是一样的，但在少数语言中（如土耳其语），Unicode大小写转换需应用特殊规则，要使用地区特定的方法才能实现正确转换。
      ```js
      let stringValue = "hello world";
      console.log(stringValue.toLocaleUpperCase()); //"HELLO WORLD"
      console.log(stringValue.toUpperCase()); //"HELLO WORLD"
      console.log(strirngValue.toLocaleLowerCase()); //"hello world"
      console.log(stringValue.toLowerCase()); //"hello world"
      // 如果不知道代码涉及什么语言，则最好使用地区特定的转换方法
      ```
  - match()--字符串模式匹配方法<br>
  这个方法本质上跟RegExp对象的exec()方法相同。match()方法接收一个参数，可以是一个正则表达式字符串，也可以是一个RegExp对象。
    ```js
    let text = "cat,bat,sat,fat";
    let pattern = /.at/;

    let matches = text.match(pattern);
    //等价于pattern.exec(text)
    console.log(matches.index); //0
    console.log(matches[0]); //"cat"
    console.log(pattern.lastIndex); //0
    ```
  - search()<br>
    这个方法唯一的参数与match()方法一样：正则表达式字符串或RegExp对象。这个方法返回模式第一个匹配的位置索引，如果没找到则返回-1.始终从字符串开头向后匹配模式
    ```js
    let text = "cat,bat,sat,fat";
    let pos = text.search(/at/);
    console.log(pos); //1
    ```
  - replace()<br>
    为了简化子字符串替换操作，ECMAScript提供了replace()方法，这个方法接收两个参数，第一个参数可以是一个RegExp对象或第一个字符串（这个字符串不会转换为正则表达式），第二个参数可以是一个字符串或一个函数，如果第一个参数是字符串，那么只会替换第一个子字符串。要想替换所有子字符串，第一个参数必须为正则表达式且带全局标记。
    ```js
    let text = "cat, bat, sat, fat";
    let result = text.replace("at","ond");
    console.log(result); //"cond,bat,sond,fond"
    console.log(text); //"cat, bat, sat, fat"--replace函数不会改变原来的字符串

    result = text.replace(/at/g,"ond");
    console.log(result); //"cond,bond,sond,fond"
    ```
    第二个参数是字符串的情况下，有几个特殊的字符序列，可以用来插入正则表达式操作的值。
    | 字符序列 | 替换文本 |
    | :---:| :---:|
    |$$|\$（这里前面的$可以理解成转义字符）|
    |$&|匹配整个模式的子字符串|
    |$`|位于匹配子串左侧的文本|
    |$'|位于匹配字串右侧的文本|
    |$1、$2、...$99|与regexp中的第1到第99个子表达式相匹配的文本（用括号来分隔不同捕获组）
    ```js
      let text = "cat,bat,sat,fat";
      result = text.replace(/(.at)/g, "word($1)");
      console.log(result); //word(cat),word(bat),word(sat),word(fat)
    ```
    replace()的第二个参数可以是一个函数。在只有一个匹配项时，这个函数会收到三个参数：与整个模式匹配的字符串、匹配项在字符串中的开始位置，以及整个字符串。在有多个捕获组的情况下，每个匹配捕获组的字符串也会作为参数传给这个函数，但最后两个参数还是与整个模式匹配的开始位置和原始字符串。这个函数应该返回一个字符串，表示应该把匹配项替换成什么。使用函数作为第二个参数可以更细致地控制替换过程。
    ```js
    function htmlEscape(text){
      return text.replace(/[<>"&]/g,dunction(match,pos,originalText){
        switch(match){
          case "<":
            return "&lt;";
          case ">":
            return "&gt;";
          case "&":
            return "&amp;";
          case "\"":
            return "&quot;";
        }
      });
    }

    console.log(htmlEscape("<p class=\"greeting\">Hello world!</p>"));
    ```
- split()<br>
  这个方法会根据传入的分隔符将字符串拆分成数组。作为分隔符的参数可以是字符串，也可以是RegExp对象（字符串分隔符不会被这个方法当成正则表达式）还可以传入第二个参数，即数组大小，确保返回的数组不会超过指定大小。
  ```js
  let colorText = "red,blue,green,yellow";
  let colors1 = colorText.split(",");//["red","blue"."green","yellow"]
  let colors2 = colorText.split(",",2);//["red","blue"]
  let colors3 = colorText.split(/[^,]+/);//["", ",", ",", ",", ""]
  ```
- localeCompare()<br>
  这个方法比较两个字符串，结果返回-1时说明字符串排在**字符串参数前面**，结果返回0说明字符串与字符串参数相等。结果返回1说明字符串排在**字符串参数后面**。
  ```js
  let stringValue = "yello";
  console.log(stringValue.localeCompare("brick")); //1
  console.log(stringValue.localeCompare("yello")); //0
  console.log(stringValue.localeCompare("zoo")); //-1
  ```
- HTML方法
  这种方法能使用JavaScript动态生成HTML标签（现在已经基本不适用）
  |方法|输出|
  |:---:|:---:|
  |big()|\<big>*string*\</big>|
  |bold()|\<b>*string*\<b>|

## 单例内置对象 
>ECMA-262对内置对象的定义时“任何由ECMAScript实现提供、与宿主环境无关，并在ECMAScript程序开始执行时就存在的对象。”因此开发者不用显示地实例化内置对象，Object、Array和String都为内置对象
### Global
Global对象是ECMAScript中最特别的对象，因为代码不会显示地访问它。它所针对的是不属于任何对象的属性和方法。事实上，不存在全局变量或全局函数这种东西，在全局作用域中定义的变量和函数都会编程Global对象的属性。isNaN()、isFinite()、parseInt()和parseFloat()都为Global对象的方法。
- URL编码方法<br>
  encodeURL()和encodeURLComponent()方法用于编码统一资源标识符（URL），以便传给浏览器。有效的URL不能包含某些字符，比如空格。使用URL编码方法来编码URL可以让浏览器能够理解他们，同时又以特殊的UTF-8编码替换掉所有无效字符。<br>
  encodeURL()方法用于对整个URL进行编码，而encodeURLComponent()方法用于编码URL中单独的组件。这两个方法的主要区别是，encodeURL()不会编码属于URL组件的特殊字符，比如冒号，斜杠，问号，井号等，而encodeURLComponent()会编码它发现的所有非标准字符。
- eval()<br>
  该方法充当一个完整的ECMAScript解释器，它接收一个参数，如果参数是表达式，则eval()计算表达式，如果参数是一个或多个javascript语句，则eval执行语句。特性：eval()参数字符串中的代码可以访问外部代码中的变量（给计算表达式提供前提），在非严格模式下，也可以将参数字符串代码中新建的变量暴露给外部代码。
  ```js
  let msg = "hello world";
  eval("console.log(msg)"); //"hello world"
  ```
- Global对象属性
  |属性|说明|
  |:----:|:----|
  |undefined|特殊值undefined|
  |NaN|特殊值NaN|
  |Infinity|特殊值Infinity（无穷数）|
  |Object|Object的构造函数|
  |Array|Array的构造函数|
  |Function|Function的构造函数|
  |Boolean|Boolean的构造函数|
  |String|String的构造函数|
  |Number|Number的构造函数|
  |Date|Date的构造函数|
  |RegExp|RegExp的构造函数|
  |Symbol|Symbol的伪构造函数|
  |Error|Error的伪构造函数|
  |EvalError|EvalError的构造函数|
- window对象
  尽管ECMA-262没有规定直接访问Global对象的方式，但浏览器将window对象实现为Global对象的代理。因此，所有全局作用域中声明的变量和函数都变成了window的属性
  ```js
  var color = "red";

  function sayColor(){
    console.log(window.color);
  }
  window.sayColor(); //"red"
  ```
  另一种获取Global对象的方式是使用如下的代码：
  ```js
  let global = function(){
    return this;
  }();
  ```
  这段代码创建一个立即调用的函数表达式，返回了this的值。当一个函数在没有明确指定this值的情况下执行时，this值等于Global对象。因此，调用一个简单返回this的函数是在任何执行上下文中获取Global对象的通用方式。
### Math
>ECMAScript提供了Math对象作为保存数学公式、信息和计算的地方。Math对象提供了一些辅助计算的属性和方法。
tips:Math对象上提供的计算要比在javascript实现的快得多，因为在Math对象上的计算使用了JavaScript引擎中更高效的实现和处理器指令。但使用Math计算的问题是精度会因浏览器、操作系统、指令集和硬件而异。
- Math对象属性
  >Math对象有一些属性，主要用于保存数学中的一些特殊值。

  |属性|说明|
  |:----:|:----:|
  |Math.E|自然对数的基数e的值|
  |Math.LN2|2为底数的自然对数|
  |Math.LN10|10为底数的自然对数|
  |Math.LOG2E|以2为底e的对数|
  |Math.LOG10E|以10为底的e的对数|
  |Math.PI| $\pi$ 的值|
  |Math.SQRT1_2|1/2的平方根|
  |Math.SQRT2|2的平方根|
- min()和max()
  Math对象也提供了很多辅助执行简单或复杂数学计算的方法。
  ```js
  let max = Math.max(2,4,6,7,1);
  console.log(max); //7

  let min = Math.min(2,4,6,7,1);
  console.log(min); //1
  ```
- 舍入方法
  Math.ceil()始终向上舍入为最接近的整数
  Math.round()执行四舍五入
  Math.fround()返回数值最接近的单精度（32位）浮点数表示
  Math.floor()始终向下舍入为最接近的整数
  ```js
  console.log(Math.ceil(25.9)); //26
  console.log(Math.ceil(25.5)); //26
  console.log(Math.ceil(25.1)); //26

  console.log(Math.round(25.9)); //26
  console.log(Math.round(25.5)); //26
  console.log(Math.round(25.1)); //25

  console.log(Math.fround(0.4)); //0.400000005960465
  console.log(Math.fround(0.5)); //0.5
  console.log(Math.fround(25.9)); //25.899999618

  console.log(Math.floor(25.9)); //25
  console.log(Math.floor(25.5)); //25
  console.log(Math.floor(25.1)); //25
  ```
- random()
  Math.random()方法返回一个0-1范围内的随机数，其中包含0但不包含1.对于希望显示随机名言或随机新闻的网页，整个方法是非常方便的。<br>
  可以基于如下公式使用Math.random()从一组整数中随机选择一个数<br>
  ```js
  number = Math.floor(Math.random() * total_number_of_choices + first_possible_value)
  //total_number_of_choices:表示可选总数，如果是1-10即为10，如果是2-10即为9
  //first_possible_value:表示起始值（最小可能的值）如果是1-10即为1
  ```
  