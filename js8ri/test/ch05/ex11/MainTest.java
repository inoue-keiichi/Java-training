package ch05.ex11;

import static org.junit.Assert.assertEquals;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import org.junit.Test;

public class MainTest {

	@Test
	public void test() {
		final ZonedDateTime departureTime = ZonedDateTime.of(LocalDateTime.of(2000, 1, 1, 14, 5),
				ZoneId.of("Europe/Berlin"));
		final ZonedDateTime arrivalTime = ZonedDateTime.of(LocalDateTime.of(2000, 1, 1, 16, 40),
				ZoneId.of("America/Los_Angeles"));
		final long actual = Main.calculateFlightSeconds(departureTime, arrivalTime);
		assertEquals(11 * 3600 + 35 * 60, actual);
	}
}
