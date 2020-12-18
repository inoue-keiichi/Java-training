package ch05.ex05;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Main {
	public static void main(final String[] args) {
		final int year = Integer.valueOf(args[0]);
		final int month = Integer.valueOf(args[1]);
		final int day = Integer.valueOf(args[2]);
		System.out.println(countDaysFrom(year, month, day) + " days have passed since you were born.");
	}

	public static long countDaysFrom(final int year, final int month, final int day) {
		final LocalDate fromDate = LocalDate.of(year, month, day);
		final LocalDate toDate = LocalDate.now();
		return fromDate.until(toDate, ChronoUnit.DAYS);
	}
}
