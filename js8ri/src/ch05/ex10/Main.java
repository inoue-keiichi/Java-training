package ch05.ex10;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class Main {
	public static void main(final String[] args) {
		final ZonedDateTime losTime = ZonedDateTime.of(LocalDateTime.of(2000, 2, 2, 15, 5),
				ZoneId.of("America/Los_Angeles"));
		System.out.println(calculateArrivalTime(losTime, "Europe/Berlin", 10, 50));
	}

	public static ZonedDateTime calculateArrivalTime(final ZonedDateTime departureZonedDateTime,
			final String arrivalZoneId, final int flightHour, final int flightMinute) {
		// フライト前の到着地の現地日時を求める
		ZonedDateTime arrivalZoneTime = ZonedDateTime.of(departureZonedDateTime.toLocalDateTime(),
				ZoneId.of(arrivalZoneId));
		final int timeDeifferenceSeconds = arrivalZoneTime.getOffset().getTotalSeconds()
				- departureZonedDateTime.getOffset().getTotalSeconds();
		arrivalZoneTime = arrivalZoneTime.plusSeconds(timeDeifferenceSeconds);

		arrivalZoneTime = arrivalZoneTime.plusHours(flightHour);
		arrivalZoneTime = arrivalZoneTime.plusMinutes(flightMinute);
		return arrivalZoneTime;
	}
}
