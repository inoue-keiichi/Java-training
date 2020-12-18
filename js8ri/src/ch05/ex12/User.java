package ch05.ex12;

import static java.time.format.DateTimeFormatter.ofPattern;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.time.LocalDateTime;
import java.time.ZoneId;

public class User implements PropertyChangeListener {
	ZoneId zoneId;

	public User(final ZoneId zoneId) {
		this.zoneId = zoneId;
	}

	@Override
	public void propertyChange(final PropertyChangeEvent evt) {
		final Schedule schedule = (Schedule) evt.getNewValue();
		final LocalDateTime localDateTime = LocalDateTime.now(zoneId).plusHours(schedule.getRemindHour());
		System.out.println(schedule.getName() + ": " + localDateTime.format(ofPattern("yyyy-MM-dd HH:mm")) + ": "
				+ zoneId.getId());
	}
}
