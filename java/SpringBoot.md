# SpringBoot

## Spring Boot概述

>Spring Boot是Spring家族的一个子项目，设计初衷是为了简化Spring配置，从而可以轻松构建独立运行的程序，并极大提高开发效率<br>

- 特点<br>
  - 可**快速构建**独立的Spring应用
  - 直接嵌入**Tomcat、jetty和Undertow**服务器（无需部署WAR文件）
  - 提供**依赖启动器**简化构建配置
  - 极大程度的**自动化**配置Spring和第三方库
  - 提供**生产就绪**功能
  - **极少**的**代码生成**和**XML配置**
- SpringBoot依赖管理
  - spring-boot-starter-parent依赖
    ```xml
    <!-- Spring Boot父项目依赖管理 -->
    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>2.13.RELEASE</version>
        <relativePath>
    </parent>
    ```
    spring-boot-starter-parent是通过\<properties>标签对一些常用技术框架的依赖文件进行了统一版本号管理。
  - spring-boot-starter-web依赖
    ```xml
    <depency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>
    ```
    spring-boot-starter-web依赖启动器的主要作用是提供web开发场景所需的底层所有依赖文件，它对Web开发场景所需的依赖文件进行了统一管理。
- Spring Boot自动配置的实现
  - Spring Boot应用的启动入口是@SpringBootApplication注解标注类中的main()方法；
  - @SpirngBootApplication能够扫描Spring组件并自动配置SpringBoot。
  - SpringBootApplication注解是一个组合注解，包含@SpringBootConfiguration、@EnableAutoConfiguration、@AomponentScan三个核心注解
- SpringBoot执行流程
  - 初始化Spring Application实例
    - 判断当前项目类型（查看classpath类路径webApplicationType下是否存在某个特征类）
    - 应用的初始化器设置（获取所有可用的应用初始化器类ApplicationContextInitializer）
    - 应用的监听设置（获取所有可用的监听器类ApplicationListener）
    - 设置项目启动类（this.mainApplicationClass=this.deduceMainApplicationClass()）
  - 初始化Spring Boot项目启动
    - 获取并运行监听器
    - 准备项目运行环境
    - 应用上下文装配（对项目应用上下文ApplicationContext的预配置）
    - 启动上下文（运行监听器启动配置好的应用上下文）
    - 运行自定义执行器（调用项目中自定义执行器）
    - 持续运行上下文（使监听器持续运行配置好的应用上下文）
  
## SpringBoot核心配置和注解

- 全局配置文件
  - application.properties配置文件
  - application.yaml配置文件<br>
    YAML文件格式是SpringBoot支持的一种JSON超集文件格式，以数据为核心。
- 配置文件属性值的注入
  - 使用@ConfigurationProperties注入属性<br>
  相关注解：
    - @Component
    - @ConfigurationProperties(prefix="xxx")
    ```java
    @component
    @ConfigurationProperties(prefix="person")
    public class Person{
      private int id;
      public void setId(int id){
        this.id=id;
      }
    }
    ```
  - 使用@Value注入属性<br>
    相关注解：
    - @Component
    - @Value
    ```java
    @component
    public class Person{
      @Value("${person.id}")
      private int id;
    }
    ```
- SpringBoot自定义配置
  - 使用@PropertySource加载配置文件<br>
    相关注解：
    - @PropertySource:指定自定义配置文件的位置和名称
    - @Configuration:自定义配置类，Spring容器组件<br>
  
    实例:
    - 创建SpringBoot项目，添加Web依赖
    - 在项目的类路径下新建一个test.properties自定义配置文件，在该配置文件中编写需要设置的配置属性。
      ```properties
      # 对实体类对象MyProperties进行属性配置
      test.id=110
      test.name=test
      ```
    - 在com.itheima.domain包下新创建一个配置类MyProperties，提供test.properties自定义配置文件中对应的属性，并根据@PropertySource注解的使用进行相关配置。
      ```java
      @Configuration
      @PropertySource("classpath:test.properties")
      @EnableConfigurationProperties(MyProperties.class)
      @ConfigurationProperties(prefix="test")
      public class MyProperties{
      }
      ```
    - 在测试类中引入MyProperties实体类Bean对象，并新增一个测试方法进行输出测试。
      ```java
      @Autowired
      private MyProperties myproperties;
      @Test
      public void myPropertiesTest(){
        System.out.println(myProperties);
      }
      ```
  - 使用@ImportResource加载XML配置文件<br>
    相关注解：<br>
    @ImportResource:指定XML文件位置<br>
    - 在项目下新建一个com.itheima.config包，并在该包下新创建一个类MyService，不需要填充
      ```java
      public class MyService{
      }
      ```
    - 在resource目录下新建一个名为beans.xml的XML自定义配置文件，在该配置文件中通过配置向Spring容器中添加MyService类对象。
      ```xml
      <?xml version="1.0" encoding="UTF-8"?>
      <beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
                      http://www.springframework.org/schema/beans/spring-beans.xsd">
      <bean id="myService" class="com.itheima.config.MyService" />
      </beans>
      ```
    - 项目启动类上添加@ImportResource注解来指定XML文件位置
    - @ImportResource("classpath:beans.xml")
    - 在测试类中引入ApplicationContext实体类Bean，并新增一个测试方法进行输出测试。
      ```java
      @Autowired
      private ApplicationContext applicationContext;
      @Test
      public void icoTest(){
        System.out.println(applicationContext.containBean())
      }
      ```
  - 使用@Configuration编写自定义配置类
- Profile多环境配置
  - 使用Profile文件进行多环境配置
  - 使用@Profile注解进行多环境配置