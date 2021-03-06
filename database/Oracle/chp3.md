<!--
 * @Author: Rooter
 * @Date: 2021-12-20 16:50:31
 * @LastEditors: Rooter
-->
# SQL*PLUS的使用

## SQL*Plus主要功能（概述）
- 数据库的维护
- 执行SQL语句执行pl/sql
- 执行SQL脚本
- 数据的导出，报表
- 应用程序开发、测试sql/plsql
- 生成新的sql脚本
- 供应用程序调用
## 使用SQL*Plus命令
- Help命令<br>
  帮助系统，向用户提供下面一些信息：<br>
  - 命令标题
  - 命令作用描述的文件
  - 命令的缩写形式
  - 命令中使用的强制参数和可选参数
  ```sql
  HELP[TOPIC] #语法格式，其中TOPIC表示需要查看的命令名称
  ```
- DESCRIBE命令<br>
  返回数据库中所存储的对象的描述。对于表、视图等对象而言，DESCRIBE命令都可以列出其各个列的名称以及各个列的属性。除此之外，DESCRIBE还会输出过程、函数和程序包的规范。
  ```sql
  describe object_name #其中object_name表示表、视图等对象的名字。
  ```
- 编辑SQL*Plus命令<br>
  SQL*Plus提供了一个命令缓冲区，用来保存最近执行的一条SQL语句，或者一个PL/SQL块。用户可以反复执行缓冲区中的内容，也可以对缓冲区中的内容进行编辑。如APPEND text...
- 执行缓冲区内容<br>
  执行缓冲区中内容的命令有两个：“/”和run；
- 编辑缓冲区内容
  使用最频繁的编辑命令是edit
  ```sql
  ED[IT][file_name]
  ```
- 对操作系统文件进行读写
  - 读文件命令
    - @ 命令：将指定的文本文件的内容读到缓冲区中并执行它
    - get 命令：将指定的文本文件的内容读到缓冲区中不执行。
  - 写文件命令
    - save命令：save命令用于将当前缓冲区中的内容写入一个操作系统文件
    - spool命令：spool命令用于将命令的执行结果输出到一个操作系统文件
- 在SQL*Plus中使用变量
  - 用户自定义的变量
    - 第一类自定义变量<br>
      1.&变量名<br>
      2.&&变量名<br>（需要多次执行，只需第一次输入值）
    - 第二类自定义变量<br>
      define 变量名=变量值
  - 参数变量<br>
    参数在SQL*Plus的命令行中指定的格式为：@文件名 参数1 参数2...
  - 与变量有关的交互式命令<br>
    - prompt命令：用来在屏幕上显示指定的字符串
    - accept命令：接收用户的键盘输入，并把用户输入的数据存放到指定的变量中，它一般与prompt命令配合使用。
    - pause命令：使当前的执行暂时停止，在用户输入回车键后继续
      