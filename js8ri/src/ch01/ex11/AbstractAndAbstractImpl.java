package ch01.ex11;

public class AbstractAndAbstractImpl implements AbstractI, AbstractJ {

	@Override
	public void f() {
		System.out.println("This is Impl class.");
	}

}
