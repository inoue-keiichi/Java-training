package ch01.ex11;

public class AbstractAndStaticImpl implements AbstractI, StaticJ {

	@Override
	public void f() {
		StaticJ.f();
	}

}
