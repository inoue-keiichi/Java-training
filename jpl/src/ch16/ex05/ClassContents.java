package ch16.ex05;

import java.lang.annotation.Annotation;
import java.lang.reflect.*;
import java.util.Objects;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

public class ClassContents {
	public static void main(String[] args) {
		try {
			Class<?> cls = Class.forName(args[0]);
			System.out.println(cls);
			Field[] fs = cls.getFields();
			Method[] ms = cls.getMethods();
			Constructor<?>[] cs = cls.getConstructors();
			printMembers(fs);
			printMembers(ms);
			printMembers(cs);

			Class<?> superC = cls.getSuperclass();
			while (Objects.nonNull(superC)) {
				printMembers(superC.getConstructors());
				superC = superC.getSuperclass();
			}

		} catch (ClassNotFoundException e) {
			System.out.println("unknown class: " + args[0]);
		}
	}

	private static void printMembers(Member[] mems) {
		for (Member mem : mems) {
			if (mem.getDeclaringClass() == Object.class)
				continue;
			String decl = mem.toString();
			System.out.print(" ");
			System.out.print(strip(decl, "java.lang."));
			System.out.print(" ");
			printAnnotations(mem);
			System.out.println();
		}
	}

	private static void printAnnotations(Member mem) {
		Annotation[] annos = null;

		if (mem instanceof Field) {
			Field f = (Field) mem;
			annos = f.getAnnotations();
		} else if (mem instanceof Method) {
			Method m = (Method) mem;
			annos = m.getAnnotations();
		} else if (mem instanceof Constructor) {
			Constructor<?> c = (Constructor<?>) mem;
			annos = c.getAnnotations();
		}
		for (Annotation ann : annos) {
			System.out.print(ann);
		}
	}

	private static String strip(String decl, String removedStr) {
		return decl.replaceFirst(removedStr, "");
	}

}
