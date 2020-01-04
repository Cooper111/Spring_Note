# 初识MyBatis

- [MyBatis映射文件UserMapper.xml](<https://blog.csdn.net/qq_44700535/article/details/94471000>)

- [超详细Mybatis入门讲解](<https://blog.csdn.net/endlessseaofcrow/article/details/80410933>)

- [【报错】彻底解决mybatis 插入数据中文后显示问号(?)的问题](https://blog.csdn.net/l1509214729/article/details/80781740)

- [【报错】对实体 "mybatis: characterEncoding" 的引用必须以 ';' 分隔符结尾](<https://www.jianshu.com/p/e140c1bd568b>)

  ```xml
  其实上面两个，加上设置ecplise设置workspace的编码，都没解决插入mysql乱码。
  最后使用<filter>解决，代码    
  	<!-- 字符编码过滤器 -->
  	<filter>
  	<filter-name>encoding</filter-name>
  	<filter-class>org.springframework.web.filter.CharacterEncodingFilter</filter-class>
  	<init-param>
  	<param-name>encoding</param-name>
  	<param-value>utf-8</param-value>
  	</init-param>
  	</filter>
  	<filter-mapping>
  	<filter-name>encoding</filter-name>
  	<url-pattern>/*</url-pattern>
  	</filter-mapping>
  ```

- `ORM`工具可以完成对象模型（`Java`）到关系模型（`Mysql`）模型的相互映射。在ORM框架中，持久化对象是一种媒介，应用程序只需要操作持久化对象，`ORM`框架则负责将这种操作转换为底层数据库操作

- ORM主流框架：`JPA，Hibernate，MyBatis`

  ```shell
  ​```Mybatis和ORM主流框架对比
  (1).其中JPA和Hibernate都对数据库结构提供了较为完整的封装，提供了从POJO到数据库表的映射关系，使用者都不需要熟练掌握SQL，Hibernate和JPA会根据指定的存储逻辑自动生成对应的SQL语句并调用对应的JDBC接口加以执行。
  
  (2).对于数据表结构不公开，或者所有牵涉业务逻辑部分的数据库操作，必须在数据库层有存储过程实现，或者由于大数据量SQL语句需要高度优化时
  ```

- 使用`Mybatis`的 **实际操作** ：

  ```java
  /**
  *1.获取SqlsessionFactory
  *2.获取Sqlsession
  *3.用面向对象的方式操作数据库
  *4.关闭事务，关闭SqlSession。
  */
  
  //e.g.
  //com.skw.tst.MybatisTest.java
  package com.skw.tst;
  
  import java.io.IOException;
  import java.io.InputStream;
  import org.apache.ibatis.io.Resources;
  import org.apache.ibatis.session.SqlSession;
  import org.apache.ibatis.session.SqlSessionFactory;
  import org.apache.ibatis.session.SqlSessionFactoryBuilder;
  
  import com.skw.domain.User;
  
  public class MyBatisTest {
  	
  	public static void main(String[] args) {
  		//创建sqlsession实例
  		SqlSession sqlSession = null;
  		try(//读取Mybatis-config.xml文件
  			InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
  		){
  			//初始化mybatis，创建sqlsessionFactory类的实例
  			SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(is);
  			sqlSession = sqlSessionFactory.openSession();
  			//创建User对象
  			User user = new User("admin","男",22);
  			//插入数据
  			sqlSession.insert("com.skw.mapper.UserMapper.insertUser", user);
  			//提交事务
  			sqlSession.commit();
  		} catch (Exception e) {
  			// 回滚事务
  			sqlSession.rollback();
  			e.printStackTrace();
  		}finally {
  			try {
  				//关闭sqlSession
  				if(sqlSession != null) {
  					sqlSession.close();
  					} 
  				} catch (Exception e2) {
  				e2.printStackTrace();
  			}
  		}
  	}
  }
  ```

  ```xml
  <!-- src/mybatis-config.xml -->
  <?xml version="1.0" encoding="UTF-8"?>
  <!DOCTYPE configuration PUBLIC "-//mybatis.org//DTD Config 3.0//EN" "mybatis-3-config.dtd" >
  <configuration>
  	<!-- 引入properties资源文件 -->
  	<properties resource="db.properties"/>
  	<!-- 指定 MyBatis 所用日志的具体实现 -->
  	<settings>
  		<setting name="logImpl" value="LOG4J"/>
  	</settings>
  	<!-- 设置别名 -->
  	<typeAliases>
  		<typeAlias  alias="user" type="com.skw.domain.User"/>
  	</typeAliases>
  	<!-- 环境配置，即连接的数据库。 -->
  	<environments default="mysql">
      <environment id="mysql">
      <!--  指定事务管理类型，type="JDBC"指直接简单使用了JDBC的提交和回滚设置 -->
        <transactionManager type="JDBC"/>
        <!--  dataSource指数据源配置，POOLED是JDBC连接对象的数据源连接池的实现。 -->
        <dataSource type="POOLED">
          <property name="driver" value="${driver}"/>
          <property name="url" value="${url}"/>
          <property name="username" value="${username}"/>
          <property name="password" value="${password}"/>
        </dataSource>
      </environment>
    </environments>
    <!-- mappers告诉了MyBatis去哪里找持久化类的映射文件 -->
    <mappers>
    	<mapper resource="com/skw/mapper/UserMapper.xml"/>
    </mappers>
  </configuration>
  ```

  ```xml
  <!--UserMapper.xml -->
  <?xml version="1.0" encoding="UTF-8"?>
  <!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "mybatis-3-mapper.dtd" >
  
  
  <mapper namespace="com.xb.mapper.UserMapper">
    	<!-- 
    	namespace="com.xb.mapper.UserMapper" 习惯上用包名+SQL映射文件名
  	id="save"是唯一的标示符
  	parameterType属性指明插入时使用的参数类型
  	useGeneratedKeys="true"表示使用数据库的自动增长策略
  	如#{name}，表示取参数中的对象的name属性值
   	-->
    <insert id="save" parameterType="com.xb.domain.User" useGeneratedKeys="true">
    	INSERT INTO TB_USER(name,sex,age) 
    	VALUES(#{name},#{sex},#{age})
    </insert>
  </mapper>
  
  ```

- #### Mapper动态代理开发

  ```
  只写接口，实现类由mybatis生成
  四个原则：Mapper接口开发需要遵循以下规范
  
  1、 Mapper.xml文件中的namespace与mapper接口的类路径相同。
  2、 Mapper接口方法名和Mapper.xml中定义的每个statement的id相同
  3、 Mapper接口方法的输入参数类型和mapper.xml中定义的每个sql 的parameterType的类型相同
  4、 Mapper接口方法的输出参数类型和mapper.xml中定义的每个sql的resultType的类型相同
  
  具体实现方法：
  1.接口与mapper.xml对应
  2.测试类 要使用sqlSession.getMapper(MapperUser.class);方法
  ```

- #### Dao开发方法

  - dao层新建接口UserDao ,实现类 UserDaoImpl

    ```java
    package com.ali.dao;
    
    import org.apache.ibatis.session.SqlSession;
    
    import org.apache.ibatis.session.SqlSessionFactory;
    
    import com.ali.bean.User;
    
    public class UserDaoImpl implements UserDao {
    
    //注入工厂
    private SqlSessionFactory sessionFactory;
    
    public UserDaoImpl(SqlSessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    //通过用户ID查询一个用户
      @Override
    public User selectUserById(Integer id) {
        SqlSession openSession = sessionFactory.openSession();
        return openSession.selectOne("user.findUserById",id);
    }
    }
    
    ```

  - 使用测试类**创建SqlSessionFactory**

    ```java
    public class MybatisDaoTest {
    public SqlSessionFactory sqlSessionFactory;
    @Before
    public void before() throws IOException {
        String resource="sqlMapConfig.xml";
        InputStream in = Resources.getResourceAsStream(resource);
        sqlSessionFactory=new SqlSessionFactoryBuilder().build(in); 
    }
    @Test
    public void testDao() {
        UserDao userDao =new UserDaoImpl(sqlSessionFactory);
        User user = userDao.selectUserById(2);
        System.out.println(user);
    }
    }
    ```

    

# 开发流程

### Mapper动态代理开发

配置`JDBC`，配置`Mybatis`，创建`Mapper(java,xml)`，`Controller`调用

- 构造`User`类和建立对应数据库表

- `spring-mvc.xml`

  ```xml
  <!-- 添加MyBatis的相关配置 -->
      <!-- 数据库连接池
      		数据库应该如何连接
       -->
       <bean id="dataSource" class="com.mchange.v2.c3p0.ComboPooledDataSource">
  		<!-- 数据库的驱动程序 -->
  		<property name="driverClass" value="com.mysql.jdbc.Driver"></property>
  		<!-- 数据库连接字符串 -->
  		<property name="jdbcUrl" value="jdbc:mysql://106.52.21.254:3306/mybatis"></property>
  		<property name="user" value="root"></property>
  		<property name="password" value="kaiwen980826"></property>
  	</bean>
       <!-- SQLSessionFactory -->
       <bean id="sqlSessionFactory" class="org.mybatis.spring.SqlSessionFactoryBean"> 
  		<property name="dataSource" ref="dataSource"></property>
  		<!-- Mybatis的核心配置文件 -->
  		<property name="configLocation" value="classpath:mybatis-config.xml"></property>
  	</bean>
  ```

- `mybatis-config.xml`

  ```xml
  <?xml version="1.0" encoding="UTF-8"?>
  <!DOCTYPE configuration PUBLIC "-//mybatis.org//DTD Config 3.0//EN" "mybatis-3-config.dtd" >
  <configuration>
  	<!-- 引入properties资源文件 -->
  	<properties resource="db.properties"/>
  	<!-- 指定 MyBatis 所用日志的具体实现 -->
  	<settings>
  		<setting name="logImpl" value="LOG4J"/>
  	</settings>
  	<!-- 设置别名 -->
  	<typeAliases>
  		<typeAlias  alias="user" type="com.skw.domain.User"/>
  	</typeAliases>
  	<!-- 环境配置，即连接的数据库。 -->
  	<environments default="mysql">
      <environment id="mysql">
      <!--  指定事务管理类型，type="JDBC"指直接简单使用了JDBC的提交和回滚设置 -->
        <transactionManager type="JDBC"/>
        <!--  dataSource指数据源配置，POOLED是JDBC连接对象的数据源连接池的实现。 -->
        <dataSource type="POOLED">
          <property name="driver" value="${driver}"/>
          <property name="url" value="${url}"/>
          <property name="username" value="${username}"/>
          <property name="password" value="${password}"/>
        </dataSource>
      </environment>
    </environments>
    <!-- mappers告诉了MyBatis去哪里找持久化类的映射文件 -->
    <mappers>
    	<mapper resource="com/skw/mapper/UserMapper.xml"/>
    </mappers>
  </configuration>
  ```

- `UserMapper.java`

  ```java
  package com.skw.mapper;
  import org.apache.ibatis.annotations.Param;
  import com.skw.domain.User;
  /*访问User表
   * @author
   */
  public interface UserMapper {
  	/**
  	 * MyBatis的查询操作，应该返回数据表中的数据
  	 * ORM数据库中的数据，映射成java中的对象
  	 * @return User对象，
  	 */
  	User selectUser(@Param("name")String name);	
  	int insertUser(User user);
  }
  ```

- `UserMapper.xml`和同包下同名接口输入输出一一对应

  ```xml
  <?xml version="1.0" encoding="UTF-8"?>
  <!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "mybatis-3-mapper.dtd" >
      <!-- 
  	1. 约束
  	2. 定义一个mapper标签, namespace设置为接口的全名
  	3. 配置方法
  		SQL语句
  		参数及返回值类型
  		方法名
   -->
  <mapper namespace="com.skw.mapper.UserMapper">
  	<!-- 需要对接口中的每一个方法进行配置
  	1）SQL语句
  		select * from user where username = ? and password = ?
  	2）配置参数及返回值
  	 -->
  	 <!-- 
  	 第一种取值方式
  	 select * from user where username = {0} and password = {1}
  	 不知道为什么有错误
  	 第二种取值方式
  	 select * from user where username = {Param1} and password = {Param2}
  	 第三种取值方式
  	 在UserMapper.java中
  	 void select(@Param("username")String username,@Param("password")String password);
  	 然后在UserMapper.xml中
  	 select * from user where username = #{username} and password = #{password}
  	  -->
  	  
  	  
  	<!-- insert操作
  	parameterType="user"表示该插入语句需要一个user对象作为参数
  	useGeneratedKeys="true"表示使用自动增长的主键 
  	-->
  	<insert id="insertUser" parameterType="com.skw.domain.User">
    	INSERT INTO tb_user(name,sex,age) VALUES(#{name},#{sex},#{age})
   	</insert>
   	
   	<!-- select操作
  	parameterType="int"表示该查询语句需要一个int类型的参数
  	resultType="user"表示返回的是一个user对象 -->
  	<select id="selectUser" resultType="com.skw.domain.User">
    	SELECT * FROM tb_user WHERE NAME = #{name}
  	</select>
    
  	<!-- update操作 
   	parameterType="user"表示该更新语句需要一个user对象作为参数-->
  <!--  	<update id="modifyUser" parameterType="user"> -->
  <!--   	UPDATE TB_USER  -->
  <!--   	SET name = #{name},sex = #{sex},age = #{age} -->
  <!--   	WHERE id = #{id} -->
  <!-- 	</update> -->
  
  	<!-- delete操作 parameterType="int"表示该查询语句需要一个int类型的参数-->
  <!--  	<delete id="removeUser" parameterType="int"> -->
  <!--  	DELETE FROM TB_USER WHERE id = #{id} -->
  <!-- 	</delete> -->
  </mapper>
  ```

- `UserController.java`内

  ```java
  	@Autowired//Spring容器自动注入对象到变量中
  	private UserMapper userMapper;
  ...
  	User user1 = userMapper.selectUser(name);
  ```




##  开发个小小项目遇到的问题

结合前面所学我试着做个用户登录注册的系统，遇到的问题：

- [java中String初始化的两种方式](<https://blog.csdn.net/abc_123456___/article/details/90706141>)

- [java判断字符及字符串是否为为空（未赋值）](<https://blog.csdn.net/weixin_39583755/article/details/80246223>)

- [multipartFile.getOriginalFilename() 空指针异常](<https://blog.csdn.net/xiaohanguo_xiao/article/details/91129329>)

- `jsp`特殊符号被转义

  ```html
  <img src="file:\D:\Eclipse\.metadata\.plugins\org.eclipse.wst.server.core\tmp2\wtpwebapps\Mybatis_test\images\${user.name}.jpg" height="60">
  
  这里的${user.name}被原样输出
  ```

- 文件上传路径，ecplise的项目在tmp中而不再原目录中，然后tamcat的路径配置啥的····最后解决方法是使用当前的根目录即可

  ```java
  在controller中，
  //getservletcontext()函数获取当前服务器对象，可以调用其下的各种方法
  String path = request.getServletContext().getRealPath("/images");这样获取上传的目录
  
  在jsp页面中，
  <img src="images/user.getName().jpg />这样
  ```

  