package ch05.ex06;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAdjuster;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class Main {
	private static final int WEEK_DAYS = 7;

	public static void main(final String[] args) {
		final List<LocalDate> days = getDates(13, DayOfWeek.FRIDAY, 1901, 2000);
		days.forEach(System.out::println);
		System.out.println("count: " + days.stream().count() + " days");
	}

	public static List<LocalDate> getDates(final int day, final DayOfWeek dayOfWeek, final int fromYear,
			final int toYear) {
		final LocalDate fromDate = LocalDate.of(fromYear, 1, 1);
		// dayOfWeekに一致する最初の日付に移動する
		LocalDate tmp = fromDate.with(next(w -> w.getDayOfWeek() == dayOfWeek));
		final List<LocalDate> result = new ArrayList<>();
		// 1週ごとにdayに合う日があれば追加する
		while (tmp.getYear() <= toYear) {
			if (tmp.getDayOfMonth() == day) {
				result.add(tmp);
			}
			tmp = tmp.plusDays(WEEK_DAYS);
		}
		return result;
	}

	private static TemporalAdjuster next(final Predicate<LocalDate> filter) {
		return (temporal) -> {
			final Period oneDay = Period.ofDays(1);
			Temporal adjustedTemporal = temporal.plus(oneDay);
			while (!filter.test(LocalDate.from(adjustedTemporal))) {
				adjustedTemporal = adjustedTemporal.plus(oneDay);
			}
			return adjustedTemporal;
		};
	}
}
