package dynamicProxy;

public class UserBImpl implements UserB{

	@Override
	public void save() {
		System.out.println("���ڱ���B���û�����");
		
	}

	@Override
	public void update() {
		System.out.println("���ڸ���B���û�����");
		
	}

}
