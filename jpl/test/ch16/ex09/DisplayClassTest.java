package ch16.ex09;

import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.junit.jupiter.api.Test;

public class DisplayClassTest {
	@Test
	void modifierTest() {

		final String[] expects = { "public static ", "private ", "protected final ", "public final " };
		int i = 0;

		try {
			Method testMethod = DisplayClass.class.getDeclaredMethod("stringConverter", int.class);
			testMethod.setAccessible(true);
			for (Field field : Class.forName("ch16.ex09.TestClass").getDeclaredFields()) {
				String actual = (String) testMethod.invoke(DisplayClass.class, field.getModifiers());
				assertEquals(expects[i], actual);
				i++;
			}
			for (Method method : Class.forName("ch16.ex09.TestClass").getDeclaredMethods()) {
				String actual = (String) testMethod.invoke(DisplayClass.class, method.getModifiers());
				assertEquals(expects[i], actual);
				i++;
			}
		} catch (SecurityException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
			fail();
		} catch (ClassNotFoundException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
			fail();
		} catch (NoSuchMethodException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
			fail();
		} catch (IllegalAccessException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
			fail();
		} catch (IllegalArgumentException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
			fail();
		} catch (InvocationTargetException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
			fail();
		}
	}

	@Test
	void typeTest() {

		final String[] expects = { "int ", "java.lang.String ", "java.lang.Object ", "java.lang.String " };
		int i = 0;

		try {
			Method testMethod = DisplayClass.class.getDeclaredMethod("stringConverter", Class.class);
			testMethod.setAccessible(true);
			for (Field field : Class.forName("ch16.ex09.TestClass").getDeclaredFields()) {
				String actual = (String) testMethod.invoke(DisplayClass.class, field.getType());
				assertEquals(expects[i], actual);
				i++;
			}
			for (Method method : Class.forName("ch16.ex09.TestClass").getDeclaredMethods()) {
				String actual = (String) testMethod.invoke(DisplayClass.class, method.getReturnType());
				assertEquals(expects[i], actual);
				i++;
			}
		} catch (SecurityException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
			fail();
		} catch (ClassNotFoundException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
			fail();
		} catch (NoSuchMethodException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
			fail();
		} catch (IllegalAccessException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
			fail();
		} catch (IllegalArgumentException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
			fail();
		} catch (InvocationTargetException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
			fail();
		}
	}
}