# 0x00 Controller

### 认识Spring MVC

- DispatcherServlet

  - Controller

  - xxxResolver

    - ViewResolver
    - HandlerExceptionResolver
    - MultipartResolver

  - HandlerMapping

    请求映射处理(到`Controller`上)的逻辑

### Spring MVC 中的常用注解

- @Controller

  - @RestController

    是`ResponseBody+Controller`

- @RequestMapping

  - @GetMapping /  @PostMapping
  - @PutMapping /  @DeleteMapping

- @RequestBody  /  @ResponseBody  /  @ResponseStatus

### 知识点

这里**项目SpringBucks**和上次差不多，放Controller：

```java
//CoffeeController
@Controller
@RequestMapping("/coffee")
public class CoffeeController {
    @Autowired
    private CoffeeService coffeeService;

    @GetMapping("/")
    @ResponseBody
    public List<Coffee> getAll() {
        return coffeeService.getAllCoffee();
    }
}

//CoffeeOrderController
@RestController
@RequestMapping("/order")
@Slf4j
public class CoffeeOrderController {
    @Autowired
    private CoffeeOrderService orderService;
    @Autowired
    private CoffeeService coffeeService;

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public CoffeeOrder create(@RequestBody NewOrderRequest newOrder) {
        log.info("Receive new Order {}", newOrder);
        Coffee[] coffeeList = coffeeService.getCoffeeByName(newOrder.getItems())
                .toArray(new Coffee[] {});
        return orderService.createOrder(newOrder.getCustomer(), coffeeList);
    }
}
```

- #### 嵌套RequestMapping

  ​    比如访问`CoffeeController`的，得先访问第一层`@RequestMapping("/order")`然后加上第二层`@PostMapping("/")`，完整链接为`http://localhost:8080/coffee/`

- #### @RequestBody 绑定，请求参数映射

​	  注意`CoffeeOrderController的create函数`，这里`@RequestBody`使用自定义的类绑定来接受映射参数。

`NewOrderRequest`类

```java
@Getter
@Setter
@ToString
public class NewOrderRequest {
    private String customer;
    private List<String> items;
}
```

发送的**POST请求**的`RequestBody`

```json
{
  "customer": "Shen Kevin",
  "items": [
    "latte", "mocha"
  ]
```

- #### Cache

首先放一个详细的学习链接，基本能懂的，[链接](<https://www.cnblogs.com/yueshutong/p/9381540.html>)

配置方法 

（1）Application上 

```java
@EnableCacheing 
```

（2）Service里

```java
//类上@CacheConfig（指定缓存名称，就是value值）
//方法上@Cacheable，如果不指定Key，则缺省按照方法的所有参数进行组合
//e.g.
//CoffeeService.java
@Service
@Slf4j
@CacheConfig(cacheNames = "CoffeeCache")
public class CoffeeService {
    @Autowired
    private CoffeeRepository coffeeRepository;

    @Cacheable
    public List<Coffee> getAllCoffee() {
        return coffeeRepository.findAll(Sort.by("id"));
    }

    public List<Coffee> getCoffeeByName(List<String> names) {
        return coffeeRepository.findByNameInOrderById(names);
    }
}
```

- #### Springboot 使用JPA对数据进行排序  

  学习链接：<https://blog.csdn.net/john_1023/article/details/90522618>

  e.g.

```java
@Cacheable
public List<Coffee> getAllCoffee() {
    return coffeeRepository.findAll(Sort.by("id"));
}
```

- #### 测试工具

  对于写好的Controller进行测试，可以使用

  - **IDEA的  Restful Toolkits**

    ![](./images/6-1.png)

  - POSTMAN

    ![](./images/6-2.png)



# 0x02 理解Spring应用上下文

- #### 回顾Aop相关知识

  在之前`SpringMVC+Mybatis企业应用实战`学习中，于`Chapter2`时学了关于Aop的一些东西，做一个回顾：

  - Aop定义、原理
  - Spring中使用AOP的两种方式（其实使用了的是**ClassPathXmlApplciation**）
    - 注解方式 (编写增强类`@Aspect`并注明切面，配置增强类`<aop:aspectj-autoproxy />`)
    - xml方式 (编写增强类-手动写方法参数为joinPoint，配置增强类配置切面)

- ![](./images/6-4.png)

- ![](./images/6-5.png)

- ![](./images/6-6.png)

- ![](./images/6-7.png)

  左边是基于xml的配置，右边是基于注解的配置

  - **基于注解**的配置，可以看到分别配置了RootConfig和ServletConfig的配置。
  - 在**xml配置**方式里，`RootConfig`的配置是通过`ContextLoaderListener`来加载的，而`ServletConfig`的配置是通过`DispatcherServlet`实现的。一般会把应用的`Service，Dao`相关的配置配置在`contextConfigLocation`，让`ContextLoaderListener`来加载

- #### ApplciationContext实现类的使用

  - Spring中使用`AnnotationConfigApplication`类实现

    - 构造Config类

      ```java
      @Configuration
      @EnableAspectJAutoProxy
      public class FooConfig {
          @Bean
          public TestBean testBeanX() {
              return new TestBean("foo");
          }
          @Bean
          public TestBean testBeanY() {
              return new TestBean("foo");
          }
          @Bean
          public FooAspect fooAspect() {
              return new FooAspect();
          }
      }
      ```

    - 传入Application

      ```java
      ApplicationContext fooContext = new AnnotationConfigApplicationContext(FooConfig.class);
      
      TestBean bean = fooContext.getBean("testBeanX", TestBean.class);
      ```

  - **不同于**之前使用 `ClassPathXmlApplciation` 时候的 

    - `<aop:config>` 、`<aop:aspect>`  (xml手动申明)

    - `<aop:aspectj-autoproxy>`(注解使用Aop)

    - 传入Application

      ```java
      //1. 创建 Spring 的 IOC 容器
      ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("beans.xml");
      
      //2. 从 IOC 容器中获取 bean 的实例
      HelloWorld  helloWorld = (HelloWorld) ctx.getBean("helloWorld");
      ```

      

- #### 实验：Context-hierarchy-demo

  - **起因**：生产环境中，父上下文为`Root WebApplicationContext`，子上下文为`Servlet WebApplicationContext`，一般想通过设置AOP切面来实现记录操作到log。但可能错误的将AOP作用于子上下文`Servlet WebApplicationContext`而导致记录操作失效。

  - **实验目的**：为研究父子上下文的AOP配置对AOP增强的支持的影响

  - TestBean.java

    ```java
    @AllArgsConstructor
    @Slf4j
    public class TestBean {
        private String context;
    
        public void hello() {
            log.info("hello: {}" + context);
        }
    }
    ```

  - FooAspect.java

    ```java
    @Aspect
    @Slf4j
    public class FooAspect {
        @AfterReturning("bean(testBean*)")
        public void printAfter() {
            log.info("after hello()");
        }
    }
    ```

  - FooConfig.java

    ```java
    @Configuration
    @EnableAspectJAutoProxy
    public class FooConfig {
        @Bean
        public TestBean testBeanX() {
            return new TestBean("foo");
        }
        @Bean
        public TestBean testBeanY() {
            return new TestBean("foo");
        }
        @Bean
        public FooAspect fooAspect() {
            return new FooAspect();
        }
    }
    ```

  - applicationContext.xml

    ```xml
    <beans xmlns="http://www.springframework.org/schema/beans"
           xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
           xmlns:aop="http://www.springframework.org/schema/aop"
           xsi:schemaLocation="http://www.springframework.org/schema/beans
            http://www.springframework.org/schema/beans/spring-beans.xsd
            http://www.springframework.org/schema/aop
            http://www.springframework.org/schema/aop/spring-aop.xsd">
    
        <aop:aspectj-autoproxy />
    
        <bean id="testBeanX" class="com.example.demo.context.TestBean">
            <constructor-arg name="context" value="Bar" />
        </bean>
    
        <!--<bean id="fooAspect" class="com.example.demo.foo.FooAspect" />-->
    </beans>
    ```

  - ContextHierarchyDemoApplication.java

    ```java
    @SpringBootApplication
    @Slf4j
    public class ContextHierarchyDemoApplication implements ApplicationRunner {
    
    	public static void main(String[] args) {
    		SpringApplication.run(ContextHierarchyDemoApplication.class, args);
    	}
    
    	@Override
    	public void run(ApplicationArguments args) throws Exception {
    		ApplicationContext fooContext = new AnnotationConfigApplicationContext(FooConfig.class);
    		ClassPathXmlApplicationContext barContext = new ClassPathXmlApplicationContext(
    				new String[] {"applicationContext.xml"}, fooContext);
    		TestBean bean = fooContext.getBean("testBeanX", TestBean.class);
    		bean.hello();
    
    		log.info("=============");
    
    		bean = barContext.getBean("testBeanX", TestBean.class);
    		bean.hello();
    
    		bean = barContext.getBean("testBeanY", TestBean.class);
    		bean.hello();
    	}
    }
    ```

  - 实验操作  (采用注解式)

    ```java
    FooConfig.java：父上下文（parent application context）。
    applicationContext.xml：子上下文（child application context）。
    
    FooConfig.java 中定义两个 testBean，分别为 testBeanX(foo) 和 testBeanY(foo)。
    applicationContext.xml 定义了一个 testBeanX(bar)。
    
    委托机制：在自己的 context 中找不到 bean，会委托父 context 查找该 bean。
    
    ----------
    
    代码解释：
    fooContext.getBean("testBeanX")，在父上下文查找 testBeanX，命中直接返回 testBeanX(foo)。
    barContext.getBean("testBeanX")，在子上下文查找 testBeanX，命中直接返回 testBeanX(bar)。
    barContext.getBean("testBeanY")，在子上下文查找 testBeanY，未命中；委托父上下文查找，命中，返回 testBeanY(foo)。
    
    ----------
    
    场景一：
    父上下文开启 @EnableAspectJAutoProxy 的支持
    子上下文未开启 <aop: aspectj-autoproxy />
    切面 fooAspect 在 FooConfig.java 定义（父上下文增强）
    
    输出结果：
    testBeanX(foo) 和 testBeanY(foo) 均被增强。
    testBeanX(bar) 未被增强。
    
    结论：
    在父上下文开启了增强，父的 bean 均被增强，而子的 bean 未被增强。
    
    ----------
     
    场景二：
    父上下文开启 @EnableAspectJAutoProxy 的支持
    子上下文开启 <aop: aspectj-autoproxy />
    切面 fooAspect 在 applicationContext.xml 定义（子上下文增加）
    
    输出结果：
    testBeanX(foo) 和 testBeanY(foo) 未被增强。
    testBeanX(bar) 被增强。
    
    结论：
    在子上下文开启增强，父的 bean 未被增强，子的 bean 被增强。
    
    ----------
    
    根据场景一和场景二的结果，有结论：“各个 context 相互独立，每个 context 的 aop 增强只对本 context 的 bean 生效”。如果想将切面配置成通用的，对父和子上下文的 bean 均支持增强，则：
    1. 切面 fooAspect 定义在父上下文。
    2. 父上下文和子上下文，均要开启 aop 的增加，即 @EnableAspectJAutoProxy 或<aop: aspectj-autoproxy /> 的支持。
    ```

  - 正确的实验结果

    ![](./images/6-3.png)