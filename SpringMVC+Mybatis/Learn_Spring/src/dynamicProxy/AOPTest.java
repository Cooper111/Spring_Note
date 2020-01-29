package dynamicProxy;

public class AOPTest {
	 
	public static void main(String[] args) {
		ProxyFactoryImpl proxyFactory = ProxyFactoryImpl.getProxyFactorySingleton();
		
		//����A���û�����
		UserA ua = (UserA) proxyFactory.newProxyInstance(new UserAImpl(), 
				new UserAHandler(new UserAImpl(), new DataValidateImpl()));
		//�õ����Ǵ������
		System.out.println(ua.getClass().getName());
		
		ua.save();
		ua.update();
		
		System.out.println("********************");
		
		//����B���û�����
		UserB ub = (UserB) proxyFactory.newProxyInstance(new UserBImpl(), 
				new UserBHandler(new UserBImpl(), new DataValidateImpl()));
		
		//�õ����Ǵ������
		System.out.println(ub.getClass().getName());
		
		ub.save();
		ub.update();
		
		//������ô��������ã����������Ľ��
		System.out.println("======================");
		UserB ub2 = new UserBImpl();
		ub2.save();
		ub2.update();
	}
}