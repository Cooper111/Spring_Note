package dunamicProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Date;



public class LogHandler implements InvocationHandler{
	
	private Object tagetObject;
	
	//对要代理的目标对象进行绑定，关联到哪个接口（与具体的实现类绑定）的哪些方法将被调用时，执行invoke方法。
	public Object newProxyInstance(Object tagetObject) {
		System.out.println("=================");
		this.tagetObject = tagetObject;
		//第一个参数是：指定目标对象的类加载器
				//第二个参数：指定目标对象实现的所有接口，让代理对象对目标对象实现同样的接口
				//第三个参数：指定代理对象要要转发请求给的实现了InvokationHandler的子类，并执行对应的invoke()方法
				//根据传入的目标对象，产生并返回改目标对象的一个代理对象，这就是动态代理
				return Proxy.newProxyInstance(tagetObject.getClass().getClassLoader(),
						tagetObject.getClass().getInterfaces(), this);
	}
	
	
	@Override
	//关联的这个实现类的方法被调用时将被执行  
    /*InvocationHandler接口的方法：proxy表示代理，method表示原对象被调用的方法，args表示方法的参数*/
	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		proxy = null;
		try {
			System.out.println("记录到日志里去……");
			proxy = method.invoke(tagetObject, args);
			System.out.println("记录成功！时间：" + new Date());
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("操作失败！！");
			throw e;
		}
		
		return proxy;
	}

}
