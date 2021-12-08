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
  - 初始化Spring Boot项目启动