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
		User user = (User) context.getBean("user");//ͨ��bean������� ��user��ʵֻ��һ���������
		User user2 = new User();
		System.out.println(user == user2);
		MyUser mu = (MyUser) context.getBean("myUser");
		//userLog.info("��ʼ����User��Add��������");
		user.addUser();//��������user2������add���Ͳ���ִ���������ǿ�߼�������
		
		//userLog.info("������������");
	}
}
