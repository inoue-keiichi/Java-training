package ch13.ex06;

public class OutOfStringIndexException extends Exception {
	public OutOfStringIndexException() {
		super("The index is out of String length");
	}

}
