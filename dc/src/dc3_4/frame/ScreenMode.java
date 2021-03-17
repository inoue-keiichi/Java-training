package dc3_4.frame;

import java.util.Objects;

public enum ScreenMode {
	CLOCK("clock"), TETRIS("tetris"), TETRIS_CLOCK("tetrisClock");

	private ScreenMode(String name) {
		this.name = name;
	}

	private String name;

	public static ScreenMode get(String name) {
		ScreenMode[] values = ScreenMode.values();
		for (ScreenMode value : values) {
			if (Objects.equals(value.toString(), name)) {
				return value;
			}
		}
		throw new IllegalArgumentException();
	}

	@Override
	public String toString() {
		return name;
	}
}
