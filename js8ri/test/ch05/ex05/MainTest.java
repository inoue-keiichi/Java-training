package ch05.ex05;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import org.junit.Test;

public class MainTest {
	@Test
	public void test() {
		final long actual = Main.countDaysFrom(1992, 8, 29);
		final long expected = ChronoUnit.DAYS.between(LocalDate.of(1992, 8, 29), LocalDate.now());
		assertEquals(expected, actual);
	}
}
