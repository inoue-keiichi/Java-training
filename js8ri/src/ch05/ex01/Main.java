package ch05.ex01;

import java.time.LocalDate;

public class Main {
	public static void main(final String[] args) {

	}

	public static String calculateProgrammersDay(final int year) {
		final LocalDate programmersDay = LocalDate.of(year, 1, 1).plusMonths(8).plusDays(12);
		return programmersDay.toString();
	}
}
