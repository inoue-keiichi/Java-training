package ch16.ex03;

public class Sample extends SuperClass {
	public int fieldA;
	public String fieldB;

	public void methodA() {

	}

	public void methodB() {

	}

}

class SuperClass extends SuperClass2 {
	public String fieldC;

	public SuperClass() {

	}

	public void methodA() {
	}

	public void methodB() {

	}

	public void methodC() {

	}
}

abstract class SuperClass2 {
	abstract void methodA();

	abstract void methodB();

	public void methodC() {

	}
}
