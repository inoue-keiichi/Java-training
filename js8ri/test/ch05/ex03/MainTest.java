package ch05.ex03;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;

import org.junit.Test;

public class MainTest {
	@Test
	public void test() {
		final LocalDate today = LocalDate.of(2020, 11, 9);
		final LocalDate actual = today.with(Main.next(w -> w.getDayOfWeek().getValue() < 6));
		assertEquals("2020-11-10", actual.toString());
	}

	@Test
	public void test_skip() {
		final LocalDate today = LocalDate.of(2020, 11, 13);
		final LocalDate actual = today.with(Main.next(w -> w.getDayOfWeek().getValue() < 6));
		assertEquals("2020-11-16", actual.toString());
	}
}
