package dunamicProxy;

public class PersonImpl implements Person{

	@Override
	public void consume(Float money) {
		System.out.println("����"+ money + "Ԫ");
	}

}
