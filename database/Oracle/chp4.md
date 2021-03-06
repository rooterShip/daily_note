<!--
 * @Author: Rooter
 * @Date: 2021-12-20 20:03:46
 * @LastEditors: Rooter
-->
# 表空间和数据文件管理

## Oracle数据库逻辑结构
- 每个数据库是由一个或多个表空间所组成（至少一个）
- 每个表空间是由一个或多个操作系统的数据文件所组成（至少一个）
- 每个表空间可以存放有一个或多个段（Segment）
- 每个段是由一个或多个区（Extent）所组成
- 每个区是由一个或多个连续的Oracle数据块组成
- 每个Oracle数据块是由一个或多个连续的操作系统数据块所组成
- 每个操作系统数据文件是由一个或多个区（Extent）所组成
- 每个操作系统数据文件是由一个或多个操作系统数据块所组成
## Oracle表空间和数据文件的概述
- 表空间类型<br>
  SYSTEM表空间（系统表空间）<br>
  TEMP表空间（临时表空间）<br>
  SYSAUX表空间（辅助系统表空间）<br>
  USERS表空间（用户表空间）<br>
  EXAMPLE表空间（实例表空间）<br>
  UNDOTBS1表空间（撤销表空间）<br>
- 表空间的状态属性<br>
  - 联机状态（ONLINE） 权限：用户方便的进行数据的访问
  - 读写状态（READ WRITE） 权限：允许用户进行对数据的查询，更新以及删除的操作
  - 只读状态（READ ONLY） 权限:只允许进行数据的查询操作
  - 脱机状态（OFFLINE） 权限：不允许用户进行对数据库的任何操作
- 表空间和数据文件的关系<br>
  数据问价是Oracle数据库中用来存储各种数据的地方，在创建表空间的同时将为表空间创建相应的数据文件。**一个数据文件只能属于一个表空间**，一个表空间可以由多个数据文件。在对数据文件进行管理时，**数据库管理员可以修改数据文件的大小、名称、增长方式和存放位置，并能够删除数据文件。**
## Oracle表空间的创建方法
在创建Oracle数据库时会自动创建SYSTEM SYSAUX和USER等表空间，但是Oracle建议根据实际需求来创建不同的非系统表空间，用来存储所有的用户对象和数据。<br>
创建表空间的命令语法结构：
```sql
CREATE [TEMPORARY | UNDO] TABLESPACE tablespace_name  #CREATE TABLESPACE my_tbs_3
[DATAFILE | TEMPFILR file_spec1] SIZE size k|M [REUSE]] #指定数据文件，如果要创建的表空间的数据文件在指定的路径中已经存在，可以使用REUSE关键字将其删除并重新创建该数据库文件
[MININUM EXTENT integer K | M] #指出在表空间中盘区的最小值
[BLOCKSIZE integer k] #设定数据块大小
[LOGGING | NOLOGGING] #指定存储在表空间中的数据库对象的任何操作是否产生日志
[force LOGGING] #表空间进入强制日志模式，忽略上一条选项
[DEFAULT {data_segment_compression} storage_clause] #声明默认的存储子句。
[ONLINE | OFFLINE] #将表空间的状态设置为联机状态或脱机状态。ONLINE是默认值
[PERMANENT | TEMPORARY] #PERMANENT表示永久存放，TEMPORARY表示临时存放
[EXTENT MANAGEMENT DICTIONARY | LOCAL] #指定表空间的管理方式
[AUTOALLOCAL | UNIFROM SIZE number] #指定表空间的盘区大小
[SEGMENT MANAGEMENT AUTO|MANUAL]#指定段空间的管理方式
```
## Oracle管理表空间和其对应数据文件的方法
- 重命名表空间和数据文件
    ```sql
    # 重命名表空间
    ALTER TABLESPACE tablespace_name RETURN TO tablespace_new_name;

    # 重命名数据文件
      # 使表空间处于OFFLINE状态
    ALTER TABLESPACE my_tbs_1 OFFLINE NORMAL;
      # 用操作系统命令重命名数据文件
    HOST RENAME D:\...\my_tbs_1_01.dbf my_tbs_1_02.dbf

      # 使用带RENAME DATAFILE字句的ALTER TABLESPACE语句改变数据文件名称
      ALTER TABLESPACE my_tbs_1
      RENAME DATAFILE 'D:\...\my_tbs_1_01.dbf' TO D:\...\my_tbs_1_02.dbf
      # 将表空间设置为联机状态
      ALTER TABLESPACE my_tbs_1 ONLINE
    ```
- 改变表空间和数据文件的状态
    ```sql
    ALTER TABLESPACE my_tbs_1 READ ONLY  #只读

    ALTER TABLESPACE my_tbs_1 READ WRITE #读写

    ALTER TABLESPACE my_tbs_1 OFFLINE # 脱机

    ALTER TABLESPACE my_tbs_1 ONLINE # 联机

    ALTER DATABASE DATAFILE file_name ONLINE | OFFLINE | OFFLINE DROP # 改变数据文件的可用性
    ```
- 设置默认表空间，扩展表精简，删除表空间及数据文件以及查看表空间和数据文件
  ```sql
  ALTER DATABASE DEFAULT TABLESPACE my_tbs_1; # 设置数据库的默认表空间
  ALTER DATABASE DEFAULT TEMPORARY TABLESPACE my_temptbs_1; #设置数据库的默认临时表空间

  # 添加新的数据文件
  ALTER TABLESPACE tablespace_name
  ADD DATEFILE ' datafilepath '
  SIZE nM;
  
  # 改变数据文件的大小
  ALTER DATABASE tablespace_name
  DATAFILE filename
  RESIZE nM;

  # 允许文件自动扩展
  ALTER DATABASE
  DATAFILE 'datafilepath'
  AUTOEXTEND ON NEXT mM MAXSIZE maxM

  # 删除表空间
  DROP TABLESPACE tablespace_name
  INCLUDE CONTENTS | INCLUDE CONTENTS AND DATAFILES; #删除对象|删除对象和数据文件

  # 删除数据文件
  ALTER TABLESPACE tablespace_name
  DROP DATAFILE 'datafilepath';
  ```
## Oracle查看表空间和数据文件信息的方法
- 查看表空间信息<br>
  为了便于对表空间进行管理，Oracle提供了一系列与表空间相关的数据字典
  | 表名 | 注释 |
  | :----: | :----: |
  | V$TABLESPACE | 从控制文件中获取的表空间名称和编号|
  | DBA_TABLESPACE | 所有用户可访问的表空间信息 |
  | USER_TABLESPACE | 用户可访问的表空间的信息 |
  | DBA_SEGMENTS | 所有表空间中的段的描述信息 |
  | USER_SEGEMENT | 用户可访问的表空间中的段的描述信息 |
  | DBA_EXTENTS | 所有用户可访问的表空间中的数据盘区的信息 |
  | USER_EXTENTS | 用户可访问的表空间中的数据盘区信息 |
  | V$DATAFILE | 所有数据文件的信息，包括所属表空间的名称和编号 |
  | V$TEMPFILE | 所有临时文件的信息，包括所属表空间的名称和编号 |
  | DBA_DATA_FILES | 所有数据文件及其所属的表空间的信息 |
  | DBA_TEMP_FIILES | 所有临时文件及其所属的表空间的信息 |
  | V$TEMP_EXTENT_POOL | 本地管理的临时表空间的缓存信息，使用的临时表空间的状态信息 |
  | V$TEMP_EXTENT_MAP | 本地管理的临时表空间的所有盘区的信息 |
  | V$SORT_USER | 用户使用的临时排序段的信息 |
  | V$SORT_SEGMENT | 例程的每个排序段的信息 |

- 查看数据文件信息<br>
  可以使用数据字典视图和动态性能视图来查看数据文件的信息
  | 表名 | 注释 |
  | :----: | :----: |
  | DBA_DATA_FILES | 包含数据库中所有数据文件的基本信息 |
  | DBA_TEMP_FILES | 包含数据库中所有临时数据文件的信息 |
  | DBA_EXTENTS | 包含所有表空间中已分配的区的描述信息，如区所属的数据文件的文件号等 |
  | USER_EXTENTS | 包含当前用户所拥有的对象在所有表空间中已分配的区的描述信息 |
  | DBA_FREE_SPACE | 包含表空间中空闲区的描述信息，如空闲区所属的数据文件的文件号等 |
  | USER_FREE_SPACE | 包含可被当前用户访问的表空间中空闲区的描述信息 | 
  | V$DATAFILE | 包含从控制文件中获取的数据文件信息，主要是用于同步的信息 |
  | V$DATAFILE_HEADER | 包含从数据文件头部获取的信息 |
