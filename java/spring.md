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
Spring框架的核心功能有：
- 创建管理所有java对象，这些java对象被称为Bean
- Spring容器管理容器中Bean之间的依赖关系，Spring使用一种被称为“依赖注入”的方式来管理Bean之间的依赖关系。
  tips:使用依赖注入不仅可以为Bean注入普通的属性值，还可以注入其他Bean的引用。依赖注入是一种优秀的**解耦**方式，其可以让Bean以配置文件组织在译器，而不是以硬编码的方式耦合在一起。