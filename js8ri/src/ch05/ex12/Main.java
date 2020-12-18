package ch05.ex12;

import java.time.ZoneId;
import java.time.ZonedDateTime;

public class Main {

	public static void main(final String[] args) {
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
	}

}
