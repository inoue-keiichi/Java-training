package main;

import java.lang.reflect.*;
import java.util.Objects;

public class StringUtils {
	public static String getNameAndParameter(Field field) {
		StringBuilder sb = new StringBuilder();
		String typeName = field.getType().getName();
		String name = field.getName();
		sb.append(omitType(typeName));
		sb.append(" ");
		sb.append(name);
		return sb.toString();
	}

	public static String getNameAndParameter(Method method) {
		final StringBuilder sb = new StringBuilder();
		final String name = method.getName();
		final Type[] paramTypes = method.getGenericParameterTypes();
		final String returnTypeName = omitType(method.getGenericReturnType().getTypeName());
		sb.append(returnTypeName);
		sb.append(" ");
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

}
