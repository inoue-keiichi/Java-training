package ch12.ex01;

public class ObjectNotFoundException extends Exception {
	public final String name;

	public ObjectNotFoundException(String name) {

		super("The Object named \"" + name + "\" is not found");
		this.name = name;
	}

}
