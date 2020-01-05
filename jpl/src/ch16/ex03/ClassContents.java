package ch16.ex03;

import java.lang.reflect.*;
import java.util.Objects;

public class ClassContents {
	public static void main(String[] args) {
		try {
			Class<?> c = Class.forName(args[0]);
			System.out.println(c);
			printMembers(c.getFields());
			printMembers(c.getConstructors());
			printMembers(c.getMethods());

			Class<?> superC = c.getSuperclass();
			while (Objects.nonNull(superC)) {
				printMembers(superC.getConstructors());
				superC = superC.getSuperclass();
			}

		} catch (ClassNotFoundException e) {
			System.out.println("unknown class: " + args[0]);
		}
	}

	private static void printMembers(Member[] mems) {
		for (Member m : mems) {
			if (m.getDeclaringClass() == Object.class)
				continue;
			String decl = m.toString();
			System.out.print(" ");
			System.out.println(strip(decl, "java.lang."));
		}
	}

	private static String strip(String decl, String removedStr) {
		return decl.replaceFirst(removedStr, "");
	}
}
