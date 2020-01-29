package dunamicProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Date;



public class LogHandler implements InvocationHandler{
	
	private Object tagetObject;
	
	//��Ҫ�����Ŀ�������а󶨣��������ĸ��ӿڣ�������ʵ����󶨣�����Щ������������ʱ��ִ��invoke������
	public Object newProxyInstance(Object tagetObject) {
		System.out.println("=================");
		this.tagetObject = tagetObject;
		//��һ�������ǣ�ָ��Ŀ�������������
				//�ڶ���������ָ��Ŀ�����ʵ�ֵ����нӿڣ��ô�������Ŀ�����ʵ��ͬ���Ľӿ�
				//������������ָ���������ҪҪת���������ʵ����InvokationHandler�����࣬��ִ�ж�Ӧ��invoke()����
				//���ݴ����Ŀ����󣬲��������ظ�Ŀ������һ�������������Ƕ�̬����
				return Proxy.newProxyInstance(tagetObject.getClass().getClassLoader(),
						tagetObject.getClass().getInterfaces(), this);
	}
	
	
	@Override
	//���������ʵ����ķ���������ʱ����ִ��  
    /*InvocationHandler�ӿڵķ�����proxy��ʾ����method��ʾԭ���󱻵��õķ�����args��ʾ�����Ĳ���*/
	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		proxy = null;
		try {
			System.out.println("��¼����־��ȥ����");
			proxy = method.invoke(tagetObject, args);
			System.out.println("��¼�ɹ���ʱ�䣺" + new Date());
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("����ʧ�ܣ���");
			throw e;
		}
		
		return proxy;
	}

}
