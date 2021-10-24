# CSS组合选择符
## 组合选择符说明了两个选择器之间的关系
- 后代选择器<br>
  >后代选择器以空格分隔<br>
  >用于选取某元素的后代元素
  ```html
    <!DOCTYPE html>
    <html>
    <head>
    <meta charset="utf-8"> 
    <title>后代选择器</title> 
    <style>
    div p
    {
        background-color:yellow;
    }
    </style>
    </head>
    <body>

    <div>
    <p>段落 1。 在 div 中。</p>
    <p>段落 2。 在 div 中。</p> <!-- 以上两个段落颜色变成黄色 -->
    </div>

    <p>段落 3。不在 div 中。</p>
    <p>段落 4。不在 div 中。</p>

    </body>
    </html>
- 子元素选择器<br>
  >子元素选择器以<q> > </q> 分隔<br>
  >**与后代选择器相比，子元素选择器只能选择作为某元素直接一级子元素的元素**
  ```html
  <!DOCTYPE html>
    <html>
    <head>
    <meta charset="utf-8"> 
    <title>子元素选择器</title> 
    <style>
    div>p
    {
        background-color:yellow;
    }
    </style>
    </head>

    <body>
    <h1>Welcome to My Homepage</h1>
    <div>
    <h2>My name is Donald</h2>
    <p>I live in Duckburg.</p>  <!-- 该段落变黄 -->
    </div>

    <div>
    <span><p>I will not be styled.</p></span>   <!-- 非直接一级标签不变黄 -->
    </div>

    <p>My best friend is Mickey.</p>
    </body>
    </html>
- 相邻兄弟选择器<br>
    >相邻兄弟选择器以<q> + </q> 分隔<br>
    >相邻兄弟选择器可选择紧接在另一元素后的元素，且二者有相同的父元素
    ```html
    <!DOCTYPE html>
    <html>
    <head>
    <meta charset="utf-8"> 
    <title>相邻兄弟选择器</title> 
    <style>
    div+p
    {
        background-color:yellow;
    }
    </style>
    </head>
    <body>

    <h1>文章标题</h1>

    <div>
    <h2>DIV 内部标题</h2>
    <p>DIV 内部段落。</p>
    </div>

    <p>DIV 之后的第一个 P 元素。</p>    <!-- div后的第一个p元素变黄 -->

    <p>DIV 之后的第二个 P 元素。</p>

    </body>
    </html>
- 后续兄弟选择器<br>
    >后续兄弟选择器以<q> ~ </q> 分隔<br>
    >后续兄弟选择器选取所有指定元素之后的相邻兄弟元素<br>
    >**区别于相邻兄弟选择器，该选择器选取所有相邻元素，并非第一个**
    ```html
    <!DOCTYPE html>
    <html>
    <head>
    <meta charset="utf-8"> 
    <title>菜鸟教程(runoob.com)</title> 
    <style>
    div~p
    {
        background-color:yellow;
    }
    </style>
    </head>
    <body>
        
    <p>之前段落，不会添加背景颜色。</p>
    <div>
    <p>段落 1。 在 div 中。</p>
    <p>段落 2。 在 div 中。</p>
    </div>

    <p>段落 3。不在 div 中。</p>
    <p>段落 4。不在 div 中。</p>    <!-- 以上两段皆为div后的指定元素，都变黄 -->

    </body>
    </html>
