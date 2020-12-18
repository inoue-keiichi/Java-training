package ch05.ex12;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

public class Scheduler {
	private static long REMIND_HOUR = 1;

	private final List<Schedule> schedules;

	public Scheduler() {
		this.schedules = new ArrayList<>();
	}

	private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);

	public void addPropertyChangeListener(final PropertyChangeListener listener) {
		pcs.addPropertyChangeListener(listener);
	}

	public void removePropertyChangeListener(final PropertyChangeListener listener) {
		pcs.removePropertyChangeListener(listener);
	}

	public void addSchedule(final String name, final ZonedDateTime zonedDateTime) {
		schedules.add(new Schedule(name, zonedDateTime, REMIND_HOUR));
	}

	public void remindSchedule() {
		final List<Schedule> tmp = new ArrayList<>();
		schedules.stream().filter(schedule -> {
			final ZoneId zoneId = schedule.getZonedDateTime().getZone();
			final LocalDateTime remindTime = LocalDateTime.now(zoneId).plusHours(REMIND_HOUR);
			return schedule.getZonedDateTime().getHour() == remindTime.getHour()
					&& schedule.getZonedDateTime().getMinute() == remindTime.getMinute();
		}).forEach(schedule -> {
			pcs.firePropertyChange("schedule", null, schedule);
			// 通知したスケジュールは削除する。forループ中に削除するとヌルポになる
			tmp.add(schedule);
		});
		tmp.forEach(e -> {
			schedules.remove(e);
		});
	}

	public boolean scheduleEmpty() {
		return schedules.size() < 1;
	}

}
