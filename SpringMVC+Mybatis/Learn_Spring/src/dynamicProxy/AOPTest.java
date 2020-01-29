package dynamicProxy;

public class AOPTest {
	 
	public static void main(String[] args) {
		ProxyFactoryImpl proxyFactory = ProxyFactoryImpl.getProxyFactorySingleton();
		
		//操作A类用户数据
		UserA ua = (UserA) proxyFactory.newProxyInstance(new UserAImpl(), 
				new UserAHandler(new UserAImpl(), new DataValidateImpl()));
		//得到的是代理对象
		System.out.println(ua.getClass().getName());
		
		ua.save();
		ua.update();
		
		System.out.println("********************");
		
		//操作B类用户数据
		UserB ub = (UserB) proxyFactory.newProxyInstance(new UserBImpl(), 
				new UserBHandler(new UserBImpl(), new DataValidateImpl()));
		
		//得到的是代理对象
		System.out.println(ub.getClass().getName());
		
		ub.save();
		ub.update();
		
		//如果不用代理来调用，就是这样的结果
		System.out.println("======================");
		UserB ub2 = new UserBImpl();
		ub2.save();
		ub2.update();
	}
}