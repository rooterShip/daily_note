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
  
