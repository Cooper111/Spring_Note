package dynamicProxy;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

public class ProxyFactoryImpl implements ProxyFactory{
	//单例模式创建工厂
	private static ProxyFactoryImpl proxyFactorySingleton;
	
	private ProxyFactoryImpl() {}
	
	public static ProxyFactoryImpl getProxyFactorySingleton() {
		if (proxyFactorySingleton == null) {
			proxyFactorySingleton = new ProxyFactoryImpl();
		}
		return proxyFactorySingleton;
	}

	@Override
	public Object newProxyInstance(Object obj, InvocationHandler handler) {
		return Proxy.newProxyInstance(obj.getClass().getClassLoader(), obj.getClass().getInterfaces(), handler);
	}
	
	
}
