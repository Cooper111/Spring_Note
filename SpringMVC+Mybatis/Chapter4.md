# 概念：国际化

- 实现国际化步骤

  ```shell
  （1）给系统通过ResourceBundle加载指定Locale对应的资源文件（国际化资源文件），再取得资源文件中指定key对应的消息
  （2）输出国际化消息，SpringMVC输出国际化消息有两种方式：
  	1.在视图页面上输出国际化消息，需要使用SpringMVC的标签库
  	2.在Controller的处理方法中输出国际化消息，需要使用```org.springframework.web.servlet.support.RequestContext的getMessage()方法
  ```

- `messageSource`接口

  ```jsp
  <!--接口名可以理解为xml里bean的id -->
  <!--使用名为messageSource的Bean，在配置xml里告诉SpringMVC国际化的属性文件保存在哪里 -->
  <bean id="messageSource"
  			class="org.springframework.context.support.ResourceBundleMessageSource"  >
  			<property name="basenames" value="messages"/>
  
  			<property name="defaultEncoding" value="UTF-8"/>
  	</bean>
  ```

- `localeResolver`接口

  ```jsp
  读取用户的浏览器语言有三种方法：
  - 读取用户浏览器的accept-language标题值
  - 读取HttpSession
  - 读取Cookie
  在SpringMVC中选择语言区域，可以使用语言解析器，接口为LocaleResolver。该接口的常用实现类都在 org.springframework.web.servlet.il8n包下面，包括：(和上面三种读取方法一一对应)
  - AcceptHeaderLocaleResolver  	不需要显式配置
  - SessionLocaleResolver			需要显式配置
  - CookieLocaleResolver			需要显式配置
  
  e.g.：（注意：得配置国际化操作拦截器）
  
  <mvc:interceptors>
  		<!-- 国际化操作拦截器如果采用基于（session/cookie）则必须配置 -->
  		<bean class="org.springframework.web.servlet.i18n.LocaleChangeInterceptor" />
  	</mvc:interceptors>
  	<!-- AcceptHeaderLocaleResolver配置，因为AcceptHeaderLocaleResolver事默认语言区域解析器，不配置也可以 -->
  	 <!-- <bean id="localeResolver" class="org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver"/>  -->
  	<!-- SessionLocaleResolver配置 -->
  	<!--  <bean id="localeResolver" class="org.springframework.web.servlet.i18n.SessionLocaleResolver"/> -->
  	<!-- CookieLocaleResolver配置 -->
  	 <bean id="localeResolver" class="org.springframework.web.servlet.i18n.CookieLocaleResolver"/> 
  
  ```

- `message`标签

  ```powershell
  在SpringMVC中显示本地化消息通常使用Spring的message标签,
  我们利用message标签的code属性来输出国际化消息
  
  使用：
  1.在配置xml里的<bean id="messageSource">内指定<property name="basenames" value="messages"/>
  2.在Jsp页面最前面使用taglib指令导入Spring的标签库：
  <%@ taglib prefix= "spring" uri="http://www.springframework.org/tags" %>
  3.Jsp页面内调用，如常见的：
  #调取本地资源文件中key为“title”的值
  <spring:message code="title" />
  
  4.在Controller中，从后台代码获取国际化信息 username
  RequestContext requestContext = new RequestContext(request);
  String username = requestContext.getMessage("username")
  ```

- [`properties`资源文件乱码解决](<https://www.cnblogs.com/dcybook/p/8143449.html>)

  ```shell
  注：
  1.properties文件存放位置：src同级目录
  2.其中{0}在此处类似一个占位符，用于将arguments中的值传入
  ​```资源文件内
  welcome = 欢迎 {0} 访问 疯狂软件
  ​```JSP界面内
  <spring:message code="welcome" arguments="${requestScope.user.username }" />
  3.如果中文资源配置文件中的中文转码，可以使用native2ascii命令来处理这个文件。
  ​```实例
  ​```简述如何在success.jsp界面中显示登陆人的username？
  首先在配置文件中中想要显示username的位置置入占位符{0}，在success.jsp界面利用message标签获取国际化信息时，利用message的argument属性设置一个参数，参数值就是user对象的username属性。显示的位置就是之前{0}的位置。
  ```

- [No message found under code 'language.cn' for locale 'zh_CN'.异常](https://blog.csdn.net/ldw201510803006/article/details/79498208)

- 如果在Controller的处理方法中输出国际化消息，需要使用什么方法来完成？

  ```powershell
  在Controller的处理方法中输出国际化消息，需要使用org.springframework.web.servlet.support.RequestContext的getMessage()方法来完成。
  
  使用方法见  message 标签
  ```

  



# 开发流程

- `SpringMVC`配置文件配置`messageSource`接口

- `SpringMVC`配置文件配置`localeResolver`接口

- 准备资源文件`messages_zh_CN.properties`，等

- 建立与资源文件对应的`jsp`界面

- 创建`User`类持久化数据

- 创建`UserController`内对应方法

  ```powershell
  "/logForm"		更改语言
  "/login"		接收表单传值
  ```

  