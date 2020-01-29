package dunamicProxy;

public class DynamicProxy {
	 
	public static void main(String[] args) {
		//给客户一个InvokationHandler对象，就能动态生成一个代理对象，进而能够访问目标对象
		LogHandler lh = new LogHandler();
		UserManager um = (UserManager) lh.newProxyInstance(new UserManagerImpl());
		System.out.println("******************");
		um.addUser("张三");
		
		//动态代理可以代理多个目标对象，减少了代码重写
		//如又代理PersonImpl,并不需要额外写其他代码
		Person p = (Person) lh.newProxyInstance(new PersonImpl());
        System.out.println("******************");
        p.consume(88f);
	}
}
