package ch01.ex11;

public interface DefaultJ {
	default void f() {
		System.out.println("This is default method of J Interface.");
	}
}
