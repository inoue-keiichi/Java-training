package ch08.ex12;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Main {
	public static void main(final String[] args) throws ClassNotFoundException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		final Class<?> clazz = Class.forName("ch08.ex12.UsingTestCase");
		final Method method = clazz.getDeclaredMethod("test");
		final int result = (int) method.invoke(clazz);
		System.out.println(result);
	}
}
