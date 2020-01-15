package ch16.ex09;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TestClass extends ArrayList<String> implements List<String>, Cloneable {
	public static int num;
	private String name;
	protected final Object user = null;

	public final String getName() {
		return name;
	}
}
