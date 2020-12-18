package ch05.ex11;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class Main {
	public static void main(final String[] args) {
		final ZonedDateTime departureTime = ZonedDateTime.of(LocalDateTime.of(2000, 1, 1, 14, 5),
				ZoneId.of("Europe/Berlin"));
		final ZonedDateTime arrivalTime = ZonedDateTime.of(LocalDateTime.of(2000, 1, 1, 16, 40),
				ZoneId.of("America/Los_Angeles"));
		final long flightSeconds = calculateFlightSeconds(departureTime, arrivalTime);

		final long hour = flightSeconds / 3600;
		final long minute = flightSeconds % 3600 / 60;
		System.out.println("The flight time is " + hour + ":" + minute + ".");
	}

	public static long calculateFlightSeconds(final ZonedDateTime departureTime, final ZonedDateTime arrivalTime) {
		final int timeDeifferenceSeconds = arrivalTime.getOffset().getTotalSeconds()
				- departureTime.getOffset().getTotalSeconds();
		return ((arrivalTime.getHour() - departureTime.getHour()) * 3600
				+ (arrivalTime.getMinute() - departureTime.getMinute()) * 60) - timeDeifferenceSeconds;
	}
}
