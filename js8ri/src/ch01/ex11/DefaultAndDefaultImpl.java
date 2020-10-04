package ch01.ex11;

public class DefaultAndDefaultImpl implements DefaultI, DefaultJ {

	@Override
	public void f() {
		DefaultI.super.f();
	}

}
