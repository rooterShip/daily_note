# MyBatis框架

## MyBatis入门

- MyBatis简介<br>
  - MyBatis属于ORM框架是一款**持久层框架**，支持SQL、存储过程及高级映射。
  - MyBatis可以使用简单的XML或注解来配置和映射原生信息，将接口和java的POJO（Plain Old Java Object，普通Java对象）映射成数据库中的记录。（MyBatis是一个半自动映射框架，它需要用户手动匹配提供POJO、SQL和映射关系，并熟练掌握SQL语言的编写）
  - MyBatis免除了几乎所有JDBC代码以及设置参数和获取结果集的工作。
  - MyBatis以第三方包的形式提供，以jar包的形式引入项目来使用。
- 入门案例
  - 导入jar包
  ```xml
   ...
  <!--mybatis依赖包-->
  <dependency>
    <groupId>org.mybatis.spring.boot</groupId>
    <artifactId>mybatis-spring-boot-starter</artifactId>
    <version>2.2.0</version>
  </dependency>

  <!--jdbc依赖包-->
  <dependency>
    <groupId>mysql</groupId>
    <artifactId>mysql-connector-java</artifactId>
  </dependency>
  ...
  ```
  - 编写pojo类
  ```java
  @Data                      //lombok 获取get,set方法
  @Accessors(chain = true)   //lombok 控制getter和setter方法的形式，chain=true返回对象
  public class User implements Serializable {
      private Integer id;
      private String name;
      private Integer age;
      private String sex;
  }
  ```
  - 创建接口
  ```java
  public interface UserMapper {
    //查询所有的User列表信息
    List<User> findAll();
  }

  ```
  - 创建映射文件
  ```xml
  <?xml version="1.0" encoding="UTF-8" ?>
  <!DOCTYPE mapper
        PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-mapper.dtd">

  <!--namespace是mybaits映射文件的唯一标识,与接口对应-->
  <mapper namespace="com.jt.mapper.UserMapper">
    <!--id 表示接口方法,resultType 返回值结果类型
    -->
    <select id="findAll" resultType="com.jt.pojo.User">
        select * from demo_user
    </select>
  </mapper>

  <!-- 此文件中通过<select>、<insert>、<update>、<delete>四种基本元素，定义了对数据库表增删改查操作的SQL语句，可以由用户根据应用性能需要进行优化编程。 -->
  ```
  - 创建mybaits-config.xml核心配置文件
  ```xml
  <?xml version="1.0" encoding="UTF-8" ?>
  <!DOCTYPE configuration
        PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-config.dtd">
    <!--核心配置文件-->
    <configuration>

        <!--环境配置标签-->
        <environments default="development">

            <!--编辑开发环境-->
            <environment id="development">
                <transactionManager type="JDBC"/>
                <dataSource type="POOLED">
                <!-- 填写对应的sql驱动以及用户名密码等 -->
                    <property name="driver" value="com.mysql.cj.jdbc.Driver"/>
                    <property name="url" value="jdbc:mysql://127.0.0.1:3306/jt?serverTimezone=GMT%2B8&amp;useUnicode=true&amp;characterEncoding=utf8&amp;autoReconnect=true&amp;allowMultiQueries=true"/>
                    <property name="username" value="root"/>
                    <property name="password" value="root"/>
                </dataSource>
            </environment>
        </environments>

        <!--Mybatis加载Mapper映射文件-->
        <mappers>
            <mapper resource="mybatis/mappers/UserMapper.xml"/>
        </mappers>
    </configuration>
  ```
  - 执行业务调用（Test）
  ```java
   @Test
    public void testDemo1() throws IOException {

        /*创建SqlSessionFactory*/
        String resource = "mybatis/mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        /*从SqlSessionFactory中获取sqlSession*/
        SqlSession sqlSession = sqlSessionFactory.openSession();

        /*获取mapper接口,执行接口方法*/
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        List<User> userList = userMapper.findAll();
        System.out.println(userList);
    }
  ```
  - 总结
    - 执行数据源配置（jar包导入）
    - 编辑POJO类实体对象，与数据库表中地段一一对应
    - 创建Mapper接口，添加接口方法
    - 创建映射文件--通过配置文件的方式实现类，要求namespace id resultType
    - 创建mybaits-config.xml核心配置文件
    - 创建SqlSessionFactory工厂对象
    - 获取SqlSession，开启数据库链接
    - 获取接口对象（代理对象）
    - 关闭sqlSession链接