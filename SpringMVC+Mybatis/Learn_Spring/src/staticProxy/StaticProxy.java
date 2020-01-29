package staticProxy;

public class StaticProxy {
	public static void main(String[] args) {
		UserManager um = new Proxy(new UserManagerImpl());
		um.addUser("£¿£¿£¿");
	}
}
