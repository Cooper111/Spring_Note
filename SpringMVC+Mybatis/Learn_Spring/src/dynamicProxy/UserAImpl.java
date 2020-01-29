package dynamicProxy;

public class UserAImpl implements UserA{

	@Override
	public void save() {
		System.out.println("正在保存A类用户……");
		
	}

	@Override
	public void update() {
		System.out.println("正在更新A类用户……");
		
	}

}
