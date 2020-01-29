package dynamicProxy;

import java.lang.reflect.InvocationHandler;

public interface ProxyFactory {
	Object newProxyInstance(Object obj, InvocationHandler handler);
}
