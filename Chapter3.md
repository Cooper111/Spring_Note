# 概念：标签库

- 要Spring的表单标签库包含在`spring-web-mvc.jar`中

- 这个库的描述符叫做`spring-form.tld`

- `SpringMVC` 的 `form` 标签主要有两个作用

  ```powershell
  1.自动绑定 Model 中的一个属性到当前 form 对应的实体对象上，默认为 command 属性，这样就可以在 form 表单中方便使用该对象的属性了。
  2.支持除 POST 和 GET 之外的方法进行提交。
  ```

- `SpringMVC`指定`form`标签默认自动绑定的是`Model`的`command`属性，那么当`form`对象对应的属性名称不是`command`时，`Spring` 提供了一个 `commandName` 属性，可以通过该属性来指定将使用`Model` 中的哪个属性作为 `form` 标签需要绑定的 `command` 对象。除了 `commandName` 属性外，指定 `modelAttribute` 属性也可以达到相同的效果

  ```shell
  #e.g.
  #即在Controller类里
  model.addAttribute("command", user);
  #然后再jsp界面
  <form:form method="post" action="register">#即可
  #无需特地指定。特定指定的话如下：
  <form:form modelAttribute="user" methood="post" action="registerForm2">
  ```

- 常见标签

  ```powershell
  （1）form 标签：绑定属性值到实体对象上，实现在表单里面使用实体对性的属性和提交表单的功能。
  （2）input 标签：通过path 属性来绑定表单数据。
  （3）password 标签：实现了密码框功能。
  （4）checkbox 和radiobutton 标签：实现了多选框和单选框的功能。
  （5）select 和 option 标签：实现了下拉框的功能。
  ```

# 开发流程

- 创建`jsp`界面，使用`标签库`

  ```jsp
  <!- 关键语句 -->
  <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
  ...
  <form:form modelAttribute="user" methood="post" action="registerForm2"></form:form>
  ```

- 创建`User`类接收`form`表单值

  ```java
  public class User implements Serializable
  //Serializable实现序列化
  //https://www.cnblogs.com/hhhshct/p/9664390.html
  ```

- 在`UserController`类中添加路由，导入`User`

  ```shell
  #1.@RequestMapping(value = "/registerForm2",method = {RequestMethod.POST,RequestMethod.GET})创建路由
  
  #2.导入User类接收Form值
  ​```
  学校ppt中直接新建User类，然后使用form来显示User类的值，这样和实际表单提交不符
  
  正确的使用表单提交，应该类似于：
  ​```public String RegisterForm2(@ModelAttribute User user,Model model)
  
  #3.将User类添加为Model的属性“command”
  ​```model.addAttribute("user", user);
  
  ​```e.g.
  private static final Log logger = LogFactory.getLog(UserController.class);
  
  @RequestMapping(value = "/registerForm2",method = {RequestMethod.POST,RequestMethod.GET})
  	//使用@ModelAttribute接收form输入来加载
  	public String RegisterForm2(@ModelAttribute User user,Model model) {
  	logger.info(user);
  		model.addAttribute("user", user);
  		//System.out.println(user.getUsername());
  		return "registerForm2";
  
  ​```每次提交都会显示User的地址，每次不一样，说明是新建的传入表单值的User
  ```




再看看提交至`Session`的：

```java
//例子适应使用普通html表单post提交、或者get提交

@RequestMapping(value="/login",method = {RequestMethod.POST,RequestMethod.GET})
	public String login(
			@RequestParam("name") String name,
			@RequestParam("password")String password,
			Model model,
			WebRequest webRequest) {
		System.out.println(name);
		System.out.println(password);
		/*登录验证
		 1.判空、格式
		 2.当前是否有用户登录
		 3.查询数据库，用户名是否存在、密码是否正确
		 4.登录成功/失败
		 */
		webRequest.setAttribute("username", name, WebRequest.SCOPE_SESSION);
		
		model.addAttribute("message","登陆成功");
		return "welcome";
	}
```



其中`weilcome.jsp`内关键语句：

```shell
${requestScope.message}
```

