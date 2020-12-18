package ch05.ex12;

import java.time.ZonedDateTime;

public class Schedule {
	private final String name;
	private final ZonedDateTime zonedDateTime;
	private final long remindHour;

	public Schedule(final String name, final ZonedDateTime zonedDateTime, final long remindHour) {
		this.name = name;
		this.zonedDateTime = zonedDateTime;
		this.remindHour = remindHour;
	}

	public String getName() {
		return name;
	}

	public ZonedDateTime getZonedDateTime() {
		return zonedDateTime;
	}

	public long getRemindHour() {
		return remindHour;
	}
}
