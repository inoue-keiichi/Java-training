package ch04.ex09;

import javafx.scene.shape.Circle;
import javafx.scene.shape.Path;

public class Planet {
	private final Circle shape;
	private final Path path;
	private final double periodMsec;

	public Planet(final Circle shape, final Path path, final double periodMsec) {
		this.shape = shape;
		this.path = path;
		this.periodMsec = periodMsec;
	}

	public Path getPath() {
		return path;
	}

	public Circle getShape() {
		return shape;
	}

	public double getPeriodMsec() {
		return periodMsec;
	}
}
