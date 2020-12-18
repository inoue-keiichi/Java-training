package ch05.ex12;

import static org.junit.Assert.assertEquals;

import java.lang.reflect.Field;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;

import org.junit.Test;

public class SchedulerTest {

	@Test
	public void test() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException,
			InterruptedException {
		final Scheduler scheduler = new Scheduler();
		final User londonUser = new User(ZoneId.of("Europe/London"));
		final User canadaUser = new User(ZoneId.of("Canada/Pacific"));
		final User japanUser = new User(ZoneId.of("Japan"));
		// scheduleを通知するユーザーを追加
		scheduler.addPropertyChangeListener(londonUser);
		scheduler.addPropertyChangeListener(canadaUser);
		scheduler.addPropertyChangeListener(japanUser);

		scheduler.addSchedule("Lunch Meeting", ZonedDateTime.now(ZoneId.of("GMT")).plusHours(1).plusMinutes(1));
		scheduler.addSchedule("Regular Meeting", ZonedDateTime.now(ZoneId.of("GMT")).plusHours(1).plusMinutes(2));

		final Class<? extends Scheduler> clazz = scheduler.getClass();
		int expected = 2;

		final Thread thread = new Thread(() -> {
			// スケジュールが無くなったら終わり
			while (!scheduler.scheduleEmpty()) {
				try {
					Thread.sleep(60 * 1000);
					scheduler.remindSchedule();
				} catch (final InterruptedException e) {
					// TODO 自動生成された catch ブロック
					e.printStackTrace();
				}
			}
		});
		thread.start();
		Thread.sleep(61 * 1000);
		final Field field = clazz.getDeclaredField("schedules");
		field.setAccessible(true);
		List<?> schedules = (List<?>) field.get(scheduler);
		expected--;
		assertEquals(expected, schedules.size());
		Thread.sleep(61 * 1000);
		schedules = (List<?>) field.get(scheduler);
		expected--;
		assertEquals(expected, schedules.size());
	}
}
