package main;

import java.lang.reflect.AnnotatedType;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import main.clazz.FieldInfo;
import main.clazz.MethodInfo;

public class StringUtils {
	public static String getNameAndParameter(Field field) {
		StringBuilder sb = new StringBuilder();
		String typeName = field.getType().getName();
		String name = field.getName();
		sb.append(name);
		sb.append("  - ");
		sb.append(stringConverter(field.getModifiers()));
		sb.append(" ");
		sb.append(omitType(typeName));
		return sb.toString();
	}

	public static String getNameAndParameter(Method method) {
		final StringBuilder sb = new StringBuilder();
		final String name = method.getName();
		final Type[] paramTypes = method.getGenericParameterTypes();
		final String returnTypeName = omitType(method.getGenericReturnType().getTypeName());
		sb.append(name);
		sb.append("(");
		for (Type paramType : paramTypes) {
			String typeName = omitType(paramType.getTypeName());
			sb.append(typeName);
			sb.append(", ");
		}
		// 余分な" ,"を消す
		if (paramTypes.length != 0) {
			sb.delete(sb.length() - 2, sb.length());
		}
		sb.append(")");
		sb.append("  - ");
		sb.append(stringConverter(method.getModifiers()));
		sb.append(" ");
		sb.append(returnTypeName);
		return sb.toString();
	}

	public static String getNameAndParameter(Constructor<?> constructor) {
		StringBuilder sb = new StringBuilder();
		String name = constructor.getName();
		Type[] paramTypes = constructor.getGenericParameterTypes();
		sb.append(omitType(name));
		sb.append("(");
		for (Type paramType : paramTypes) {
			String typeName = omitType(paramType.getTypeName());
			sb.append(typeName);
			sb.append(", ");
		}
		// 余分な" ,"を消す
		if (paramTypes.length != 0) {
			sb.delete(sb.length() - 2, sb.length());
		}
		sb.append(")");
		return sb.toString();
	}

	private static String omitType(final String typeName) {
		if (!(Objects.equals(typeName, "java.lang.String") || Objects.equals(typeName, "java.lang.Object"))) {
			return typeName;
		}
		return typeName.replace("java.lang.", "");
	}

	public static boolean macthRegex(final String instance) {
		final String strRegex = "^\\$\\{.*\\}$";
		return instance.matches(strRegex);
	}

	public static String getInstanceKey(final String instanceName) {
		//  instanceのkeyがなかったらエラー
		if (!StringUtils.macthRegex(instanceName)) {
			throw new IllegalArgumentException();
		}
		return instanceName.substring(2, instanceName.length() - 1);
	}

	public static List<FieldInfo> createFieldInfos(final Object instance)
			throws IllegalArgumentException, IllegalAccessException {
		final Class<?> clazz = instance.getClass();
		final List<FieldInfo> fieldInfos = new ArrayList<>();
		boolean accessible;
		int modifier;
		Type type;

		// フィールドは修飾子、型つける。public以外も表示する
		for (Field field : clazz.getDeclaredFields()) {
			accessible = field.isAccessible();
			try {
				field.setAccessible(true);
				modifier = field.getModifiers();
				type = field.getGenericType();
				FieldInfo fieldInfo = new FieldInfo();
				StringBuilder prefix = new StringBuilder();
				prefix.append(stringConverter(modifier));
				prefix.append(stringConverter(type));
				fieldInfo.setPrefix(prefix.toString());
				fieldInfo.setName(field.getName());
				fieldInfo.setValue(field.get(instance));
				fieldInfos.add(fieldInfo);
			} finally {
				field.setAccessible(accessible);
			}
		}
		return fieldInfos;
	}

	public static List<MethodInfo> createMethodInfos(final Object instance)
			throws IllegalArgumentException, IllegalAccessException {
		final Class<?> clazz = instance.getClass();
		final List<MethodInfo> methodInfos = new ArrayList<>();
		boolean accessible;
		int modifier;
		Type type;

		// メソッドは修飾子、返り値の型つける。public以外も表示する
		for (Method method : clazz.getDeclaredMethods()) {
			accessible = method.isAccessible();
			try {
				method.setAccessible(true);
				modifier = method.getModifiers();
				type = method.getGenericReturnType();
				MethodInfo methodInfo = new MethodInfo();
				StringBuilder prefix = new StringBuilder();
				prefix.append(stringConverter(modifier));
				prefix.append(stringConverter(type));

				methodInfo.setPrefix(prefix.toString());
				methodInfo.setName(method.getName());
				methodInfo.setArguments(method.getGenericParameterTypes());
				methodInfo.setExceptions(method.getGenericExceptionTypes());
				methodInfos.add(methodInfo);
			} finally {
				method.setAccessible(accessible);
			}
		}
		return methodInfos;
	}

	public static StringBuilder printClass(final Object instance)
			throws SecurityException, ClassNotFoundException, IllegalArgumentException, IllegalAccessException {
		final StringBuilder classContent = new StringBuilder();
		final Class<?> clazz = instance.getClass();
		classContent.append(clazz.getPackage());
		classContent.append("\n\n");
		return printClass(instance, "", classContent);
	}

	private static StringBuilder printClass(final Object instance, final String space, final StringBuilder classContent)
			throws SecurityException, ClassNotFoundException, IllegalArgumentException, IllegalAccessException {
		final Class<?> clazz = instance.getClass();
		boolean accessible;
		int modifier;
		Type type;
		String name;
		String spaceTab = space;

		// classの修飾子を表示
		AnnotatedType superClazz = clazz.getAnnotatedSuperclass();
		AnnotatedType[] interfaceClazzes = clazz.getAnnotatedInterfaces();
		classContent.append(space);
		classContent.append(stringConverter(clazz.getModifiers()));
		classContent.append("class ");
		classContent.append(clazz.getSimpleName());
		if (Objects.nonNull(superClazz) && !superClazz.getType().getTypeName().equals("java.lang.Object")) {
			classContent.append(" extends ");
			classContent.append(superClazz.getType().getTypeName());
		}
		if (interfaceClazzes.length > 0) {
			classContent.append(" implements ");
			for (int i = 0; i < interfaceClazzes.length; i++) {
				classContent.append(interfaceClazzes[i].getType().getTypeName());
				if (i != interfaceClazzes.length - 1) {
					classContent.append(" ,");
				}
			}
		}
		spaceTab += "	";
		classContent.append(" {");
		classContent.append("\n");
		// フィールドは修飾子、型つける。public以外も表示する
		for (Field field : clazz.getDeclaredFields()) {
			accessible = field.isAccessible();
			try {
				field.setAccessible(true);
				modifier = field.getModifiers();
				type = field.getGenericType();
				name = field.getName();
				classContent.append(spaceTab);
				classContent.append(stringConverter(modifier));
				classContent.append(stringConverter(type));
				classContent.append(name);
				classContent.append(" = ");
				classContent.append(field.get(instance));
				classContent.append("\n");
			} finally {
				field.setAccessible(accessible);
			}
		}
		// メソッドは修飾子、返り値の型つける。public以外も表示する
		for (Method method : clazz.getDeclaredMethods()) {
			accessible = method.isAccessible();
			try {
				method.setAccessible(true);
				modifier = method.getModifiers();
				type = method.getGenericReturnType();
				name = method.getName();
				Type[] params = method.getGenericParameterTypes();
				if (params.length < 1) {
					classContent.append(spaceTab);
					classContent.append(stringConverter(modifier));
					classContent.append(stringConverter(type));
					classContent.append(name);
					classContent.append("() ");
				} else {
					classContent.append(spaceTab);
					classContent.append(stringConverter(modifier));
					classContent.append(stringConverter(type));
					classContent.append(name);
					classContent.append("( ");
					for (int i = 0; i < params.length; i++) {
						classContent.append(stringConverter(params[i]));
						if (i < params.length - 1) {
							classContent.append(" ,");
						}
					}
					classContent.append(") ");
				}
				if (method.getGenericExceptionTypes().length > 1) {
					classContent.append("throws ");
					Type[] errorClazzes = method.getGenericExceptionTypes();
					for (int i = 0; i < errorClazzes.length; i++) {
						classContent.append(errorClazzes[i].getTypeName());
						if (i < errorClazzes.length - 1) {
							classContent.append(" ,");
						}
					}
				}
			} finally {
				classContent.append("\n");
				method.setAccessible(accessible);
			}
		}

		classContent.append(space + "}");
		classContent.append("\n");
		return classContent;
	}

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

}
