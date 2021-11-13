# HTML中的Javascript
## \<script>元素
***
- 将Javascript嵌入HTML的主要方法是使用\<script>元素,\<script>元素属性有：
  - async:可选。表示应该立即开始下载脚本，但不能阻止其他页面动作，比如下载资源或等待其他脚本加载。只对外部脚本文件有效
  - charset:可选。使用 src 属性指定的代码字符集。这个属性很少使用，因为大多数浏览器不在乎它的值。
  - defer：可选。表示脚本可以延迟到文档完全被解析和显示之后再执行。只对外部脚本文件有效
  - crossorigin：可选。配置相关请求的CORS(跨资源共享)设置。默认不使用CORS。
  - integrity：可选。允许比对接收到的资源和指定的加密签名以验证子资源完整性。如果接收到的资源的签名与这个属性指定的签名不匹配，则页面会报错，脚本不会执行(用于引用别人放在服务器上的javascrpt文件时，防止恶意程序员替换文件)
  - src：可选。表示包含要执行的代码的外部文件。
  - type：可选。代替language，表示代码块中脚本语言的内容类型。<br>
  在不出现defer和integerity时，浏览器会按照\<script>在页面中出现的顺序依次解释它们
- 使用\<script>的方式有两种：
  - 直接在网页中嵌入JavaScript代码
  ```html
  <script>
    function sayHi(){
      console.log("HI!")
    }
  </script>
  <!-- 代码中不能出现字符串</script>，如果要写必须加上转义字符 -->
  ```
  - 在网页中包含外部JavaScript文件--必须使用src属性，该属性的值是一个url，指向包含javascript代码的文件
  ```html
  <script src="example.js"></script>
  <!--  使用了 src 属性的<script>元素不应该再在<script>和</script>标签中再包含其他 JavaScript代码。如果两者都提供的话，则浏览器只会下载并执行脚本文件，从而忽略行内代码-->
  ```  
- 标签位置
  - 将\<script>元素放在\<head>标签内--弊端：必须等到所有的Javascript代码都下载，解析和解释完成后，才能开始渲染页面，期间浏览器页面保持空白
  - 将\<script>元素放在\<body>标签内--（习惯写法）
- 推迟执行脚本
  ```html
  <!DOCTYPE html>
  <html>
    <head>
      <title>Example HTML Page</title>
      <script defer src="example1.js"></script>
      <script defer src="examole2.js"></script>
    </head>
    <body>
      <!-- 这里是页面内容 -->
    </body>
    </html>
  ```
  例子中的\<script>元素包含在页面的\<head>中，但会等到浏览器解析到结束的\</html>标签后才会执行（defer属性--只对外部脚本文件有效），但推迟执行的脚本不一定总会按顺序执行，因此最好只包含一个这样的脚本。
- 异步执行脚本
  ```html
  <!DOCTYPE html>
  <html>
    <head>
      <title>Example HTML Page</title>
      <script async src="example1.js"></script>
      <script async src="example2.js"></script>
    </head>
    <body>
      <!-- 这里是页面内容 -->
    </body>
  </html>
  ```
  脚本添加async属性的目的是告诉浏览器，不必等脚本下载和执行完后再加载页面，也不必等到该**异步**脚本下载和执行后再加载其他脚本。
- 动态加载脚本<br>
  除了\<script>标签，还可以通过向DOM中动态添加script元素来加载指定脚本
  ```js
  let script = document.createElement('script'); //创建一个script元素
  script.src = 'gibberish.js';
  document.head.appendChild(script); //添加到DOM
  ```
  默认情况下，以这种方式创建的\<script>元素是以异步方式加载的，相当于添加了async属性。由于不是所有的浏览器都支持async属性，可以将其设置为同步加载来统一动态脚本的加载行为
  ```js
  let script = document.createElement('script'); //创建一个script元素
  script.src = 'gibberish.js';
  script.async = false;
  document.head.appendChild(script);
  ```
  以这种方式获取的资源对浏览器预加载器是不可见的，会严重影响它们在资源获取队列中的优先级。我们可以通过在文档头部显示声明它们：
  ```html
  <link rel="preload" href="gibberish.js"> 
  ```
  来让预加载器知道这些动态请求文件的存在。
## 行内代码与外部文件
***
在通常的实践中，我们更倾向于将javascript代码放在外部文件中，其优点如下:
- 可维护性：不做过多解释
- 缓存：浏览器会根据特定的设置缓存所有外部链接Javascript文件，这意味着如果两个页面都用到同一个文件，则该文件只需下载一次，因此页面加载更快。
- 适应未来：将Javascript放到外部文件中，就不必考虑用XHTML，包含外部Javascript文件的语法在HTML和XHTML中是一样的<br>
## \<noscript>元素
***
\<noscript>元素被用于给不支持javascript的浏览器提供替代内容<br>
\<noscript>元素可以包含除\<script>任何可以出现在<body>中的HTML元素<br>
以下情况浏览器会显示包含在<noscript>中的内容：
- 浏览器不支持脚本
- 浏览器对脚本的支持被关闭

  
