package main.clazz;

public class FieldInfo implements MemberInfo {
	private String prefix;
	private String name;
	private Object value;

	public String getPrefix() {
		return prefix;
	}

	public String getName() {
		return name;
	}

	public Object getValue() {
		return value;
	}

	public void setPrefix(final String prefix) {
		this.prefix = prefix;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public void setValue(final Object value) {
		this.value = value;
	}
}
