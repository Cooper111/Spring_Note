package dynamicProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Date;

public class UserBHandler implements InvocationHandler {
	
	private UserBImpl umi;
	private DataValidateImpl dvi;
	
	public UserBHandler(UserBImpl umi, DataValidateImpl dvi) {
		this.umi = umi;
		this.dvi = dvi;
	}
	
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		proxy = null;
		try {
			System.out.println("记录到日志里去……");
			proxy = method.invoke(umi, args);
			System.out.println("记录成功！时间：" + new Date());
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("操作失败！！");
			throw e;
		}
		
		return proxy;
	}

}
