package ch16.ex05;

import ch16.ex05.ReflectionAnnotation.*;
import ch16.ex05.ReflectionAnnotation.MethodAnnotation;

public class Sample extends SuperClass {
	@FieldAnnotation
	public int fieldA;
	@FieldAnnotation
	public String fieldB;

	@ConstructorAnnotation
	public Sample() {

	}

	@MethodAnnotation
	public void methodA() {

	}

	@MethodAnnotation
	public void methodB() {

	}

}

class SuperClass extends SuperClass2 {
	@FieldAnnotation
	public String fieldC;

	@ConstructorAnnotation
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
