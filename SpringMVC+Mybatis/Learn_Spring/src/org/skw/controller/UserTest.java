package org.skw.controller;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class UserTest {
	//private static Logger userLog = Logger.getLogger(User.class);
	@Test
	public void testUser(){
		
		ApplicationContext context = new ClassPathXmlApplicationContext("/org/skw/controller/springmvc-config.xml");
		User user = (User) context.getBean("user");//通过bean容器获得 的user其实只是一个代理对象
		User user2 = new User();
		System.out.println(user == user2);
		MyUser mu = (MyUser) context.getBean("myUser");
		//userLog.info("开始调用User的Add方法……");
		user.addUser();//把这里变成user2来调用add，就不会执行切面的增强逻辑功能了
		
		//userLog.info("正常结束……");
	}
}
