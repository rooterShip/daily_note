# Spring简介
- Spring是一个从实际开发中抽取出来的框架，因此它完成了大量开发中的通用步骤，留给开发者的仅仅是与特定应用相关的部分。
- 优点：
  - 低侵入式设计，代码的污染极低
  - 独立于各种应用的服务器，基于Spring框架的应用，实现*Write Onece,Run Anywhere*
  - Spring的loC容器降低了业务对象替换的复杂性，提高了组件之间的解耦。(loC意味着将开发人员设计好的对象交给容器控制：①收集（bean）和注册；②分析和组装)
  - Spring的AOP支持允许将一些通用任务如安全、事务、日志等进行集中式管理，从而提供了更好的复用。
  - Spring的ORM和DAO提供了第三方持久层框架的良好整合，并简化了底层的数据库访问
  - Spring的高度开放性，并不强制应用完全依赖于Spring，开发者可自由选用Spring框架的部分或全部
# Spring的核心机制
## 框架组件
- Spring Core<br>
  核心容器，提供Spring框架的基本功能，其主要组件BeanFactory是**工厂模式**的实现。通过控制反转（loc）机制，将应用程序配置和依赖性规范与实际的程序代码分离开。
- Spring Context<br>
  向Spring框架提供上下文信息，包括企业服务，如JNDI、EJB、电子邮件、国际化、校验和调度等。
- Spring AOP<br>
  通过配置管理特性，可以很容易地使Sprig框架管理的任何对象支持AOP。Spring AOP直接将面向方面编程的功能集成到Spring框架中。 它为基于Spring应用程序的对象提供了事务管理服务。
- Spring DAO<br>
  JDBC DAO抽象层提供了有用的异常层次结构，用来管理异常处理和不同数据库供应商抛出的错误消息。异常层次结构简化了错误处理，面向JDBC的异常符合通用的DAO异常层次结构。
- Spring ORM(对象关系映射，用面向对象的思想去操作数据库中的表）<br>
  Spring框架插入了若干的ORM框架，提供了ORM的对象关系工具，其中包括JDO(持久化新规范)、Hibernate和iBatis SQL Map，并且都遵从Spring的通用事务和DAO异常层次结构。
- Spring Web
  为基于Web的应用程序提供上下文。建立在应用程序上下文模块之上。
- Spring Web MVC
  是一个全功能构建Web应用程序的MVC实现。
## 管理Bean
***
程序主要是通过Spring容器来访问容器中的Bean,ApplicationContext是Spring容器最常用的接口，该接口有如下两个实现类：
- ClassPathXmlApplicationContext: 从类加载路径下搜索配置文件，并根据配置文件来创造Spring容器
- FileSystemXmlApplicationContext：从文件系统的相对路径或绝对路径下去搜索配置文件，并根据配置文件来创造Spring容器
```java
public class BeanTest{
  public static void main(String[] args) throws Exception{
    ApplicationContext ctx = new ClassPathXmlApplicationContext("beans.xml");
    Person p = ctx.getBean("person",Person.class);
    p.say();
  }
}
```
## 依赖注入
***
- Spring框架的核心功能有：
  - 创建管理所有java对象，这些java对象被称为Bean
  - Spring容器管理容器中Bean之间的依赖关系，Spring使用一种被称为“依赖注入”的方式来管理Bean之间的依赖关系。
    tips:使用依赖注入不仅可以为Bean注入普通的属性值，还可以注入其他Bean的引用。依赖注入是一种优秀的**解耦**方式，其可以让Bean以配置文件组织在译器，而不是以硬编码的方式耦合在一起。<br>
- 理解依赖注入<br>
  当某个java对象（调用者）需要调用另一个java对象（被依赖对象）的方法时，在传统模式下通常由两种做法：
  - 原始做法：调用者**主动**创建被依赖对象，然后再调用被依赖对象的方法。
  - 简单工厂模式：调用者先找到被依赖对象的工厂，然后**主动**通过工厂去获取被依赖对象，最后再调用被依赖对象的方法。<br>
  ***
  因为是调用者主动调用被调用者，所以会导致调用者与被依赖对象实现类的**硬编码耦合**，使用spring框架之后，调用者无需主动获取被依赖对象，调用者只要**被动**接收Spring容器为调用者的成员变量赋值即可，这种方式被成为控制反转（Inverse of Control，loC）或者是依赖注入（dependency injection)。
## 设值注入（setter注入）
***
设值注入是指loC容器通过成员变量的setter方法来注入被依赖对象。这种注入方式简单直观。
```java
//构建Human的接口Human.java
public interface Human{
  public void speak();
}

//构建Language的接口Language.java
public interface Language{
  public String kind();
}

//实现Human的接口Chinese.java
public class Chinese implements Human{
  private Language lan;
  public void speak(){
    System.out.println(lan.kind());
  }
  //设值注入内核
  public void setLan(Language lan){
    this.lan = lan;
  }
}

//实现Language的接口English
public class English implements Language{
  public String kind(){
    return "讲英语";
  }
}
```
```xml
<!-- 按照传统模式，为了将Language类型的变量传入到Chinese中去，要同时声明两个对象实例才能引用，现在利用spring的设值注入进行配置 -->
<?xml version="1.0" encoding="UTF-8"?>
  <beans>
  <!--定义第一个Bean，注入Chinese类对象 -->
  <bean id="chinese" class="Chinese">
  <!-- property元素用来指定需要容器注入的属性，lan属性需要容器注入 ref就指向lan注入的id -->
    <property name="lan" ref="english"></property>
  </bean>
  <!-- 注入English -->
  <bean id="english" class="English"></bean>
</beans>
```
```java
//测试代码
import org.springframework.context.applicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
public class Test{
  public static void main(String[] agrs){
    ApplicationContext ctx = new FileSystemXmlApplicationContext("src/applicationContext.xml");
    Human human = new Human();
    human = null;
    human = (Human)ctx.getBean("chinese");
    human.speak();
  }
}
```
## 构造注入
***
利用构造器来设置依赖关系的方式，被成为构造注入。即驱动Spring在底层以反射方式执行带指定参数的构造器，当执行带参数的构造器时，就可利用构造器参数对成员变量执行初始化。
```java
...
//对chinese类进行修改,通过构造函数实现对lan值的注入--构造注入
public class chinese implements Human{
  private Language lan;
  public chinese(Language lan){
    this.lan = lan;
  }
  public void speak(){
    System.out.println(lan.kind());
  }
}
...
```
```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans>
  <!-- 定义第一个Bean,注入Chinese类对象 -->
  <bean id="chinese" class="Chinese">
  <!-- 使用构造注入，为Chinese实例注入Language实例 -->
    <constructor-arg="english"></constructor-arg>
  </bean>
  <!-- 注入English -->
  <bean id="english" class="English">
  </bean>
</beans>
```
## 两种注入方式对比
***
- 设值注入优点：
  - 与传统的JavaBean的写法更相似，程序开发人员更容易理解、接收。通过setter方法设定依赖关系显得更加直观，自然。
  - 对于复杂的依赖关系，如果采用构造注入，会导致构造器过于臃肿，难以阅读，Spring在创建Bean实例时，需要同时实例化其依赖的全部实例，性能下降。
  - 在某些成员变量可选的情况下，多参数的构造器更加笨重
- 构造注入优点:
  - 构造注入可以在构造器中决定依赖关系的注入顺序顺序，优先依赖的优先注入。
  - 对于依赖关系无需变化的Bean,构造注入更有用处，没有setter的情况下，所有依赖关系全部在构造器内设定，无需担心后续的代码对依赖关系产生破坏。
  - 依赖关系只能在构造器中设定，则只有组件的创建者才能改变组件的依赖关系，对组件的调用者而言，组件内部的依赖关系完全透明，符合高内聚。<br>
    **tips**:建议采用设置注入为主，构造注入为辅的注入策略。对于依赖关系无须变化的注入，尽量采用构造注入；而其他依赖关系的注入，则考虑采用设值注入。
## Spring容器中的Bean
***
对于开发者来说，开发者使用Spirng框架主要是做两件事：
 - 开发Bean
 - 配置Bean<br>
***
Spring框架就是根据配置文件来创建Bean实例，并调用Bean实例的方法完成**依赖注入**--loC
### 容器中Bean的作用域
当通过Spring容器创建一个Bean实例时,不仅可以完成Bean实例的实例化，还可以为Bean指定特定的作用域。Spring支持如下五种作用域：
- singleton:单例模式，在整个Spring loC容器中，singleton作用域Bean将只生成一个实例。
- prototype：每次通过容器的getBean()方法获取prototype作用域的Bean时，都将产生一个新的Bean实例
- reques：对于一次HTTP请求，request作用域的Bean将只生成一个实例，这意味着，在同一次HTTP请求内，程序每次请求该Bean，得到的总是同一个实例。只有在web应用中使用Spring时，该作用域才真正有效。
- session：该作用域将bean的定义限制为HTTP会话，只有在web-aware Spring ApplicationContext的上下文中有效
- global session：每个全局的HTTP Session对应一个Bean实例
## 创建Bean的方式
***
- 使用构造器创建Bean实例
- 使用静态工厂创建Bean
- 调用实例工厂方法创建Bean
# Spring的AOP
## AOP简介
***
AOP(Aapect Orient Programming)--面向切面编程，作为面向对象编程的一种补充<br>
AOP专门用于处理系统中分布于各个模块（不同方法）中的交叉关注点的问题，在javaEE应用中，常用AOP处理一些具有横切性质的系统级的服务，如事务管理
、安全检查、缓存、对象池管理等
## 使用AspectJ实现AOP
AspectJ是一个基于Java语言的AOP框架，提供了强大的AOP功能，其他很多AOP框架都借鉴或采纳其中的一些思想。其主要包括两个部分：一个部分定义了如何表达、定义AOP编程中的语法规范，通过这套语法规范，通过这套规范，可以方便地用AOP来解决Java语言中存在的交叉关注点的问题；另一个部分时工具部分，包括编译调试工具等。<br>
AOP实现可分为：
- 静态AOP实现：AOP框架在编译阶段对程序进行修改，即实现对目标的增强，生成静态的AOP代理类，以AspectJ为代表
- 动态AOP实现：AOP框架在运行阶段动态生成AOP代理，以实现对目标对象的增强，以Spring AOP为代表
## AOP的基本概念
关于面向切面编程的一些术语：
- 切面（Aspect）：切面用于组织多个Advice，Advice放在切面中定义
- 连接点（Joinpoint）：程序执行过程中明确的点，如方法的调用，或者异常的抛出。在Spring AOP中，连接点总是方法的调用。
- 增强处理（Advice）: AOP框架在特定的切入点执行的增强处理。处理有"around"、"before"和"after"等类型
- 切入点（pointcut）：可以插入增强处理的连接点。
***
## 代理机制初探
- 问题由来<br>
  程序种经常需要为某些动作或事件做记录，以便随时检查
# note_src:www.runoob.com



  