package ch05.ex01;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class MainTest {
	@Test
	public void test() {
		final String actual = Main.calculateProgrammersDay(2014);
		assertEquals("2014-09-13", actual);
	}

	@Test
	public void test_leapYear() {
		final String actual = Main.calculateProgrammersDay(2012);
		assertEquals("2012-09-13", actual);
	}
}
