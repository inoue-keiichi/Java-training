package ch05.ex03;

import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAdjuster;
import java.util.function.Predicate;

public class Main {

	public static TemporalAdjuster next(final Predicate<LocalDate> filter) {
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
