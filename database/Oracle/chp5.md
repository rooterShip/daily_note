<!--
 * @Author: Rooter
 * @Date: 2021-12-21 09:48:33
 * @LastEditors: Rooter
 * @LastEditTime: 2021-12-21 13:56:42
-->
# Oracle模式对象

## 表的创建及管理方法
>表是常见的一种组织数据的方式、一张表一般具有多个列（字段），每个字段都具有特定的属性，包括字段名、字段数据类型、字段长度、约束和默认值等，这些属性在创建表时被确定。从用户角度来看，数据库中数据的逻辑结构是一张二维表，在表中通过行和列来组织数据。在表中的每一行存放一条信息，通常称表中的一行为一条记录，通过ROWID来标识。<br>
创建表时需要使用CREATE TABLE语句，为了在自己的模式种创建一个新表，用户必须具有CREATE TABLE系统权限。如果要在其他用户模式种创建表必须具有CREATE ANY TABLE的系统权限。此外，用户还必须在指定的表空间种具有一定的配额存储空间
- 创建表
    ```sql
    # 以创建一个学生表（student_1）为例
    CREATE TABLE student_1(
        sno CHAR(10),
        sname VARCHAR(30),
        ssex CHAR(2),
        sbirthday DATE,
        sdept VARCHAR2(30)
    );

    # 利用子查询创建一个表（emp_select），表中包括职工号（emp_no）、职工姓名（emp_name）和职工所在部门号（dept_no)，该表用于保存工资高于2000的员工的员工号、员工名和部门号
    CREATE TABLE emp_select(
        emp_no,emp_name,dept_no
    )
    AS
    SELECT empno,ename,deptno FROM emp WHERE sa1>2000;
    ```
- 管理表
  ```sql
  # 增加字段
  ALTER TABLE table_name
  ADD(column_name1 datatype,column_name2 datatype...);

  # 修改字段名称
  ALTER TABLE table_name
  RENAME COLUMN column_name TO new_column_name;

  # 修改字段的数据类型
  ALTER TABLE table_name
  MODIFY column_name new_datatype;

  # 删除字段
  ALTER TABLE table_name
  DROP COLUMN column_name;

  # 重命名表
  RENAME table_name TO new_table_name;

  # 移动表
  ALTER TABLE table_name MOVE TABLESPACE tablespace_name
  ```
## 表的约束的定义及使用方法
- NOT NULL：非空约束
  ```sql
  # 定义NOT NULL约束
  CONSTRAINT sname_notnull NOT NULL
  ```
- UNIQUE：唯一性约束
  ```sql
  # 定义单个UNIQUE约束
  CONSTRAINT semail_unique UNIQUE

  # 定义多个UNIQUE约束
  CONSTRAINT semail_unique UNIQUE （stelephone,semail)
  ```
- PRIMARY KEY：主键约束
- FOEIGN KEY：外键约束
- CHECK：检查约束
## 视图的创建及管理方法
>视图是从一个或多个表或视图中提取出来的数据的一种表现方式，并不存储真实的数据，不占用实际的存储空间，只是在数据字典中保存它的定义信息，所以视图被认为是“存储的查询”或“虚拟的表”
- 创建简单视图(基于单个表或者不对子查询检索的字段进行函数或数学计算的视图)
  ```sql
  CREATE VIEW emp_view1
  AS
  SELECT empno,ename,sal
  FROM emp WHERE deptno=30;
- 创建复杂视图（基于多个表或者对子查询检索的字段进行函数或数学计算的视图）
    ```sql
    CREATE VIEW emp_view2
    AS
    SELECT empno,ename,sal*1.15 new_sal
    FROM emp WHERE sal*1.15>2000;

    CREATE VIEW emp_view3
    AS
    SELECT empno,ename,sal,dname
    FROM emp, dept
    WHERE emp.deptno=detp.deptno;
    ```
## 索引的分类、创建及管理方法
## 索引的创建、使用及管理方法
## 同义词的创建及删除方法