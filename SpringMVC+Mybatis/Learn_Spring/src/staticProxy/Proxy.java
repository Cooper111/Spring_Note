package staticProxy;

import java.util.Date;

public class Proxy implements UserManager{
	
	private UserManagerImpl umi;
	
	public Proxy(UserManagerImpl umi) {
		this.umi = umi;
	}
	
	@Override
	public void addUser(String userName) {
		//ʹ�ô����һ���ô��ǿ����ڶ�ʵ�ʶ������ǰ����һЩ��Ҫ�Ĵ��������˶�ʵ�ʶ���ķ���
		//�����֮ǰ��һЩ��־����
		System.out.println("���֮ǰ����¼����־��ȥ����");
		umi.addUser(userName);
		//�����֮��Ҳ��һЩ��־����
		System.out.println("��¼�ɹ���ʱ�䣺" + new Date());
	}
	
}
