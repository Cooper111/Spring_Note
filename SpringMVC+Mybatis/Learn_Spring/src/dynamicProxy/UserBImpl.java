package dynamicProxy;

public class UserBImpl implements UserB{

	@Override
	public void save() {
		System.out.println("正在保存B类用户……");
		
	}

	@Override
	public void update() {
		System.out.println("正在更新B类用户……");
		
	}

}
