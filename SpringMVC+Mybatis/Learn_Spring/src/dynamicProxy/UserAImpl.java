package dynamicProxy;

public class UserAImpl implements UserA{

	@Override
	public void save() {
		System.out.println("���ڱ���A���û�����");
		
	}

	@Override
	public void update() {
		System.out.println("���ڸ���A���û�����");
		
	}

}
