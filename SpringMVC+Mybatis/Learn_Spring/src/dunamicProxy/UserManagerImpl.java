package dunamicProxy;

public class UserManagerImpl implements UserManager{

	@Override
	public void addUser(String userName) {
		System.out.println("添加用户成功!用户为：" + userName);	
	}

}
