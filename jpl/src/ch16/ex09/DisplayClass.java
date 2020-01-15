package ch16.ex09;

import java.lang.reflect.AnnotatedType;
import java.lang.reflect.*;
import java.util.Objects;

public class DisplayClass {
	public static void printClass(final String className) throws SecurityException, ClassNotFoundException {
		final Class<?> clazz = Class.forName(className);
		System.out.println(clazz.getPackage());
		System.out.println("");
		printClass(className, "");
	}

	private static void printClass(final String className, final String space)
			throws SecurityException, ClassNotFoundException {
		final Class<?> clazz = Class.forName(className);
		boolean accessible;
		int modifier;
		Type type;
		String name;
		String spaceTab = space;

		// classの修飾子を表示
		AnnotatedType superClazz = clazz.getAnnotatedSuperclass();
		AnnotatedType[] interfaceClazzes = clazz.getAnnotatedInterfaces();
		System.out.print(space + stringConverter(clazz.getModifiers()) + "class " + clazz.getSimpleName());
		if (Objects.nonNull(superClazz) && !superClazz.getType().getTypeName().equals("java.lang.Object")) {
			System.out.print(" extends " + superClazz.getType().getTypeName());
		}
		if (interfaceClazzes.length > 0) {
			System.out.print(" implements ");
			for (int i = 0; i < interfaceClazzes.length; i++) {
				System.out.print(interfaceClazzes[i].getType().getTypeName());
				if (i != interfaceClazzes.length - 1) {
					System.out.print(" ,");
				}
			}
		}
		spaceTab += "	";
		System.out.println(" {");
		// フィールドは修飾子、型つける。public以外も表示する
		for (Field field : clazz.getDeclaredFields()) {
			accessible = field.isAccessible();
			try {
				field.setAccessible(true);
				modifier = field.getModifiers();
				type = field.getGenericType();
				// Type typea = field.getGenericType();
				name = field.getName();
				System.out.println(spaceTab + stringConverter(modifier) + stringConverter(type) + name);
			} finally {
				field.setAccessible(accessible);
			}
		}
		// メソッドは修飾子、返り値の型つける。public以外も表示する
		// Type[] exception;
		for (Method method : clazz.getDeclaredMethods()) {
			accessible = method.isAccessible();
			try {
				method.setAccessible(true);
				modifier = method.getModifiers();
				type = method.getGenericReturnType();
				// exception = method.getGenericExceptionTypes();
				name = method.getName();
				Type[] params = method.getGenericParameterTypes();
				if (params.length < 1) {
					System.out.print(spaceTab + stringConverter(modifier) + stringConverter(type) + name + "() ");
				} else {
					System.out.print(spaceTab + stringConverter(modifier) + stringConverter(type) + name + "( ");
					for (int i = 0; i < params.length; i++) {
						System.out.print(stringConverter(params[i]));
						if (i < params.length - 1) {
							System.out.print(" ,");
						}
					}
					System.out.print(") ");
				}
				if (method.getGenericExceptionTypes().length > 1) {
					System.out.print("throws ");
					Type[] errorClazzes = method.getGenericExceptionTypes();
					for (int i = 0; i < errorClazzes.length; i++) {
						System.out.print(errorClazzes[i].getTypeName());
						if (i < errorClazzes.length - 1) {
							System.out.print(" ,");
						}
					}
				}
			} finally {
				System.out.println("");
				method.setAccessible(accessible);
			}
		}

		for (Class<?> innerClazz : clazz.getDeclaredClasses()) {
			printClass(innerClazz.getName(), spaceTab);
		}

		System.out.println(space + "}");
	}

//	private static String stringConverter(Class<?> type) {
//		if (!type.isArray()) {
//			return type.getName() + " ";
//		}
//		return stringConverter(type.getComponentType()) + "[] ";
//	}

	private static String stringConverter(Type type) {

		if (!type.getClass().isArray()) {
			return type.getTypeName() + " ";
		}
		return stringConverter(type.getClass().getComponentType()) + "[] ";
	}

	private static String stringConverter(int modifier) {
		StringBuilder sb = new StringBuilder();
		if (Modifier.isPrivate(modifier))
			sb.append("private ");
		if (Modifier.isPublic(modifier))
			sb.append("public ");
		if (Modifier.isProtected(modifier))
			sb.append("protected ");
		if (Modifier.isStatic(modifier))
			sb.append("static ");
		if (Modifier.isAbstract(modifier))
			sb.append("abstract ");
		if (Modifier.isFinal(modifier))
			sb.append("final ");
		if (Modifier.isInterface(modifier))
			sb.append("interface ");
		if (Modifier.isNative(modifier))
			sb.append("native ");
		if (Modifier.isStrict(modifier))
			sb.append("strict ");
		if (Modifier.isSynchronized(modifier))
			sb.append("synchoronized ");
		if (Modifier.isTransient(modifier))
			sb.append("transient ");
		if (Modifier.isVolatile(modifier))
			sb.append("volatile ");
		return sb.toString();
	}

	public static void main(String[] args) {
		try {
			DisplayClass.printClass("ch16.ex09.TestClass");
			DisplayClass.printClass("ch16.ex09.TestClass2");
		} catch (SecurityException | ClassNotFoundException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
	}
}
