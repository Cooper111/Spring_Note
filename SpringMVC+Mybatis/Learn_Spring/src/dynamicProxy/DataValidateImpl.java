package dynamicProxy;


public class DataValidateImpl implements DataValidate {
 
	@Override
	public void validate() {
		System.out.println("���ڽ�������У�顭��");
		System.out.println("����У����ϣ�");
		
	}
 
	@Override
	public void advice() {
		System.out.println("�����ɹ�");
		
	}
 
}

