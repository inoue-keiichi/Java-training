package ch08.ex06;

import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.Stream;

import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;

public class Main {
	public static void main(final String[] args) {
		final Point2D[] point2Ds = { new Point2D(1, 1), new Point2D(3, 1), new Point2D(1, 0), new Point2D(4, 2),
				new Point2D(1, -2), new Point2D(3, 1) };
		Arrays.sort(point2Ds, Comparator.comparing(Point2D::getX).thenComparing(Point2D::getY));
		Stream.of(point2Ds).forEach(System.out::println);

		final Rectangle2D[] rectangles = { new Rectangle2D(0, 0, 0, 0), new Rectangle2D(-5, -3, 10, 0),
				new Rectangle2D(0, 0, 10, 0), new Rectangle2D(3, 5, 10, 10), new Rectangle2D(3, 5, 10, 1) };
		Arrays.sort(rectangles, Comparator.comparing(Rectangle2D::getMinX).thenComparing(Rectangle2D::getMinY)
				.thenComparing(Rectangle2D::getWidth).thenComparing(Rectangle2D::getHeight));
		Stream.of(rectangles).forEach(System.out::println);
	};
}
