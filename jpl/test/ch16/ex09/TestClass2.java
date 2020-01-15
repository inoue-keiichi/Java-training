package ch16.ex09;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TestClass2 {
	String name;
	HashMap<String, List<Object>> map;

	private class Inner {
		int num;
		String name;
	}

	private enum item {
		Ringo, Mikan, Budo
	}

	public final String setName(final String name) {
		return name;
	}

	public final List<Object> getValue(final String key) {
		return map.get(key);
	}

	public final void display(HashMap<String, Object> map) {
	}

	public final void display(int[] nums) {
	}

	public final char[] getArray() {
		return null;
	}

	public void throwError() throws SecurityException, ClassNotFoundException {
		new IOException();

	}
}