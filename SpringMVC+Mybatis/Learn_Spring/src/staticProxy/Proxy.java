package staticProxy;

import java.util.Date;

public class Proxy implements UserManager{
	
	private UserManagerImpl umi;
	
	public Proxy(UserManagerImpl umi) {
		this.umi = umi;
	}
	
	@Override
	public void addUser(String userName) {
		//使用代理的一个好处是可以在对实际对象访问前进行一些必要的处理，控制了对实际对象的访问
		//在添加之前做一些日志操作
		System.out.println("添加之前，记录到日志里去……");
		umi.addUser(userName);
		//在添加之后也做一些日志操作
		System.out.println("记录成功！时间：" + new Date());
	}
	
}
