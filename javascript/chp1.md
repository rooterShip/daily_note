# 什么是JavaScript
## Javascript实现
***
完整的Javascript实现包含：
>核心(ECMAScript)<br>
>文档对象模型(DOM)<br>
>浏览器对象模型(BOM)<br>
- ECMAScript<br>
  - ECMAScript,即ECMA-262定义的语言，无输入和输出方法。在基本层面，ECMA-262描述这门语言的**语法**、**类型**、**语句**、**关键字**、**保留字**、**操作符**、**全局对象**。
  - ECMAScript符合性：要成为ECMAScript实现，必须满足下列条件：
    - 支持ECMA-26中描述的所有“类型、值、对象、属性、函数，以及程序语法与定义”
    - 支持Unicode字符标准<br>
    还可以满足：
    - 增加ECMA-262中未提及的“额外的类型、值、对象、属性和函数”。
    - 支持ECMA-262中没有定义的“程序和正则表达式语法”--允许修改和扩展内置的正则表达式特性。
- DOM
  - 文档对象模型（DOM，Document Object Model）是一个应用编程接口（API），用于在HTML中使用扩展的XML。DOM将整个页面抽象为一组分层节点。HTML或XML页面的每个组成部份都是一种节点，包含不同的数据。<br>
    eg:
    ```html
    <html>
      <head>
        <title>Sample Page</title>
      </head>
      <body>
        <p> Hello world!</p>
      <body>
    </html>
    ```
    DOM通过创建表示文档的树，让开发者可以随心所欲地控制网页地内容和结构。使用DOM API，可以轻松地删除、添加、替换、修改节点。
  - 