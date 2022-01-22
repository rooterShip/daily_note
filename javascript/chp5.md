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
Boolean的实例会重写valueOf()方法，返回一个原始值true或false。
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

