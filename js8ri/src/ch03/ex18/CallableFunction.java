package ch03.ex18;

@FunctionalInterface
public interface CallableFunction<T, R> {
	R call(T t) throws Exception;
}
