package ch01.ex11;

public interface DefaultI {
	default void f() {
		System.out.println("This is default method of I Interface.");
	}
}
