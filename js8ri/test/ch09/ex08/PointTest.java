package ch09.ex08;

import static org.junit.Assert.*;

import org.junit.jupiter.api.Test;

public class PointTest {

	@Test
	public void test() {
		Point point = new Point(5, 2);
		Point other1 = new Point(3, 3);
		Point other2 = new Point(6, 8);
		Point other3 = new Point(5, 1);
		Point other4 = new Point(5, 7);
		Point other5 = new Point(5, 2);

		assertTrue(point.compareTo(other1) > 0);
		assertTrue(point.compareTo(other2) < 0);
		assertTrue(point.compareTo(other3) > 0);
		assertTrue(point.compareTo(other4) < 0);
		assertEquals(0, point.compareTo(other5));
	}
}
