# Spring MVC

## 概述

Spring MVC是Spring提供的一个强大而零或的web框架。借助于注解，Spring MVC提供了几乎是POJO的开发模式，使得控制器的开发和测试更加简单。这些控制器一般不直接处理请求，而是将其委托给Spring上下文中的其他Bean,通过Spring的**依赖注入**功能，这些bean被注入到控制器中。

Spring MVC主要由DispatcherServlet、处理器映射、处理器（控制器）、视图解析器、视图组成。<br>
核心有：<br>
- 处理器映射：选择使用哪个控制器来处理请求
- 视图解析器：选择结果应该如何渲染

## 运行原理

- 1.Http请求：客户端请求提交到DispatcherServlet
- 2.寻找处理器：由DispatcherServlet控制器查询一个或多个HandlerMapping，找到处理请求的Controller。
- 3.调用处理器：DispatcherServlet将请求提交到Controller。
- 4.调用业务处理和返回结果：Controller调用业务逻辑处理（xxxServicce）后，返回ModelAndView。
- 5.处理视图映射并返回模型：DispatcherServlet查询一个或多个ViewResoler视图解析器，找到ModelAndView指定的视图。
- 6.Http响应：视图负责将结果显示到客户端。

## Spring MVC接口解释

- DispatcherServlet接口；<br>
  Spring提供的前端控制器，所有的请求都经过它统一分发。在DispatcherServlet请求分发给Spring Controller之前，需要借助于Spring提供的HandlerMapping定位到具体的Controller。
- HandlerMapping接口：<br>
  完成客户请求到Controller映射
- Controller接口：<br>
  需要为并发用户处理上述请求，因此实现Controller接口时，必须保证线程安全并且可重用。Controller将处理用户请求，一旦Controller处理完用户请求，则返回ModelAndView对象给DispatcherServlet前端控制器，而ModelAndView是Http请求过程中返回的模型（Model）和视图（View）。
- ViewResolver接口：<br>
  Spring提供的视图解析器（ViewResolver）在web应用中查找View对象，从而将相应结果渲染给客户。

## DispatcherServlet

为Spring MVC的核心。负责接收HTTP请求组织协调Spring MVC的各个组成部分。主要工作有：
- 截获符合特定格式的URL请求
- 初始化DispatcherServlet上下文对应WebApplicationContext，并将其与业务层、持久化层的WebApplicationContext建立关联。
- 初始化Spring MVC的各个组成组件，并装配到DispatcherServlet中。

## Spring MVC配置
- 在web.xml中配置springmvc-context.xml（springmvc-config.xml)的路径。（springmvc-config中即实现前端控制器DispatcherServlet的功能）
- 配置springmvc-context.xml(springmvc-config.xml)文件，开启注解功能，识别controller，配置视图解析器。
- 编写java代码
