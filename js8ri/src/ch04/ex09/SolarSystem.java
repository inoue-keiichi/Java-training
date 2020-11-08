package ch04.ex09;

import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcTo;
import javafx.scene.shape.Circle;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;

public class SolarSystem {
	private final Circle sun;
	private final Planet mercury;
	private final Planet venus;
	private final Planet earth;
	private final Planet mars;
	private final Planet jupiter;

	public SolarSystem() {
		this.sun = buildSun();
		this.mercury = buildPlanet(2, Color.BLUE, 6000, 110, 110);
		this.venus = buildPlanet(3, Color.GOLD, 1500, 130, 120);
		this.earth = buildPlanet(5, Color.SANDYBROWN, 4000, 150, 150);
		this.mars = buildPlanet(8, Color.RED, 5000, 200, 250);
		this.jupiter = buildPlanet(11.2, Color.GREEN, 8000, 250, 200);
	}

	private Circle buildSun() {
		final Circle result = new Circle(0, 0, 100, Color.ORANGE);
		return result;
	}

	private Planet buildPlanet(final double radius, final Color color, final double periodMsec,
			final double orbitXRadius, final double orbitYRadius) {
		final Circle shape = new Circle(0, 0, radius, color);
		shape.centerXProperty().bind(Bindings.add(sun.centerXProperty(), orbitXRadius));
		shape.centerYProperty().bind(sun.centerYProperty());
		final Path path = createPath(orbitXRadius, orbitYRadius, sun.centerXProperty(), sun.centerYProperty());
		return new Planet(shape, path, periodMsec);
	}

	private Path createPath(final double xRadius, final double yRadius, final DoubleProperty centerXProperty,
			final DoubleProperty centerYProperty) {
		final MoveTo moveTo = createMoveTo(xRadius, centerXProperty, centerYProperty);
		final ArcTo firstHalfArcTo = createFirstHalfArcTo(xRadius, yRadius, centerXProperty);
		final ArcTo secondHalfArcTo = createSecondHalfArcTo(xRadius, yRadius, centerXProperty);

		final Path path = new Path();
		path.getElements().add(moveTo);
		path.getElements().add(firstHalfArcTo);
		path.getElements().add(secondHalfArcTo);
		path.setStroke(Color.DODGERBLUE);
		return path;
	}

	private ArcTo createFirstHalfArcTo(final double xRadius, final double yRadius,
			final DoubleProperty centerXProperty) {
		final ArcTo arcTo = new ArcTo();
		arcTo.setRadiusX(xRadius);
		arcTo.setRadiusY(yRadius);
		arcTo.xProperty().bind(Bindings.subtract(centerXProperty, xRadius));
		arcTo.yProperty().bind(centerXProperty);
		return arcTo;
	}

	private ArcTo createSecondHalfArcTo(final double xRadius, final double yRadius,
			final DoubleProperty centerXProperty) {
		final ArcTo arcTo = new ArcTo();
		arcTo.setRadiusX(xRadius);
		arcTo.setRadiusY(yRadius);
		arcTo.xProperty().bind(Bindings.add(centerXProperty, xRadius));
		arcTo.yProperty().bind(centerXProperty);
		return arcTo;
	}

	private MoveTo createMoveTo(final double radius, final DoubleProperty centerXProperty,
			final DoubleProperty centerYProperty) {
		final MoveTo moveTo = new MoveTo();
		moveTo.xProperty().bind(Bindings.add(centerXProperty, radius));
		moveTo.yProperty().bind(centerYProperty);
		return moveTo;
	}

	public Circle getSun() {
		return sun;
	}

	public Planet getMercury() {
		return mercury;
	}

	public Planet getVenus() {
		return venus;
	}

	public Planet getEarth() {
		return earth;
	}

	public Planet getMars() {
		return mars;
	}

	public Planet getJupiter() {
		return jupiter;
	}
}
