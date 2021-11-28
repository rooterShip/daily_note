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
