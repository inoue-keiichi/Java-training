package ch05.ex10;

import static org.junit.Assert.assertEquals;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import org.junit.Test;

public class MainTest {

	@Test
	public void test() {
		final ZonedDateTime losTime = ZonedDateTime.of(LocalDateTime.of(2000, 2, 2, 15, 5),
				ZoneId.of("America/Los_Angeles"));
		final ZonedDateTime actual = Main.calculateArrivalTime(losTime, "Europe/Berlin", 10, 50);
		assertEquals("2000-02-03T10:55+01:00[Europe/Berlin]", actual.toString());
	}
}
