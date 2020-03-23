package main.clazz;

import java.lang.reflect.Type;

public class MethodInfo implements MemberInfo {
	private String prefix;
	private String name;
	private Type[] arguments;
	private Type[] exceptions;

	public String getPrefix() {
		return prefix;
	}

	public String getName() {
		return name;
	}

	public Type[] getArguments() {
		return arguments;
	}

	public Type[] getExceptions() {
		return exceptions;
	}

	public void setPrefix(final String prefix) {
		this.prefix = prefix;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public void setArguments(final Type[] arguments) {
		this.arguments = arguments;
	}

	public void setExceptions(final Type[] exceptions) {
		this.exceptions = exceptions;
	}
}
