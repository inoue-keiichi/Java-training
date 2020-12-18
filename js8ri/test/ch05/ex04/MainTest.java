package ch05.ex04;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class MainTest {
	@Test
	public void test() {
		final String expected = "          1  2  3  4\n" + " 5  6  7  8  9 10 11\n" + "12 13 14 15 16 17 18\n"
				+ "19 20 21 22 23 24 25\n" + "26 27 28 29 30 31";

		final String actual = Main.createCalendar(10, 2020);
		assertEquals(expected, actual);
	}

	@Test
	public void test_noSpace() {
		final String expected = "" + " 1  2  3  4  5  6  7\n" + " 8  9 10 11 12 13 14\n" + "15 16 17 18 19 20 21\n"
				+ "22 23 24 25 26 27 28";

		final String actual = Main.createCalendar(2, 2021);
		assertEquals(expected, actual);
	}
}
