package dc4.frame.clock;

public enum ClockType {
	ANALOG("analog"), DEGITAL("degital"), TETRIS("tetris");

	private String name;

	private ClockType(String name) {
		this.name = name;
	}

	public static ClockType get(String name) {
		switch (name) {
		case "analog":
			return ANALOG;
		case "degital":
			return DEGITAL;
		case "tetris":
			return TETRIS;
		default:
			throw new IllegalArgumentException();
		}
	}

	public String toString() {
		return name;
	}
}
