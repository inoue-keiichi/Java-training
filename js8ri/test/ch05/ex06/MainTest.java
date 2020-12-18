package ch05.ex06;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;

import org.junit.Test;

public class MainTest {
	@Test
	public void test() {
		final List<LocalDate> actual = Main.getDates(13, DayOfWeek.FRIDAY, 2020, 2024);
		final LocalDate[] expected = new LocalDate[8];
		expected[0] = LocalDate.of(2020, 3, 13);
		expected[1] = LocalDate.of(2020, 11, 13);
		expected[2] = LocalDate.of(2021, 8, 13);
		expected[3] = LocalDate.of(2022, 5, 13);
		expected[4] = LocalDate.of(2023, 1, 13);
		expected[5] = LocalDate.of(2023, 10, 13);
		expected[6] = LocalDate.of(2024, 9, 13);
		expected[7] = LocalDate.of(2024, 12, 13);
		assertEquals(expected.length, actual.size());
		assertArrayEquals(expected, actual.toArray(new LocalDate[actual.size()]));

	}
}
