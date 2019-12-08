package ch13.ex05;

public class BadDataSetException extends Exception {
	public String name;

	public BadDataSetException(String name) {
		super("The number is only permitted: " + name);
		this.name = name;
	}
}
