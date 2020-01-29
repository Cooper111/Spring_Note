package org.skw.xmlAop;

import org.junit.Test;
import org.skw.xmlAop.MyUser;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class XmlMain {
	@Test
	public void testUser() {
		ApplicationContext context = new ClassPathXmlApplicationContext("/org/skw/xmlAop/springmvc-config.xml");
		MyUser mu = (MyUser) context.getBean("MyUser");
		mu.addUser();
		
	}
}
