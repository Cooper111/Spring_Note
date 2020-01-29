package dynamicProxy;


public class DataValidateImpl implements DataValidate {
 
	@Override
	public void validate() {
		System.out.println("正在进行数据校验……");
		System.out.println("数据校验完毕！");
		
	}
 
	@Override
	public void advice() {
		System.out.println("操作成功");
		
	}
 
}

