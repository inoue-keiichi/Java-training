package ch05.ex04;

import java.time.DayOfWeek;
import java.time.LocalDate;

public class Main {
	public static void main(final String args[]) {
		final int month = Integer.valueOf(args[0]);
		final int year = Integer.valueOf(args[1]);
		System.out.println(createCalendar(month, year));
	}

	public static String createCalendar(final int month, final int year) {
		final LocalDate date = LocalDate.of(year, month, 1);
		return createSpace(date.getDayOfWeek()) + createDays(date);
	}

	private static String createSpace(final DayOfWeek dayOfWeek) {
		String result = "";
		for (int i = 0; i < dayOfWeek.getValue() - 1; i++) {
			result = result + "   ";
		}
		return result;
	}

	private static String createDays(final LocalDate date) {
		String result = "";
		int count = date.getDayOfWeek().getValue();
		for (int i = 1; i <= date.lengthOfMonth(); i++) {
			result = result + convertFormattedDay(i);
			if (count < DayOfWeek.SUNDAY.getValue()) {
				result = result + " ";
				count++;
			} else {
				result = result + "\n";
				count = DayOfWeek.MONDAY.getValue();
			}
		}
		return result.substring(0, result.length() - 1);
	}

	private static String convertFormattedDay(final int day) {
		if (day < 1 || day > 31) {
			throw new IllegalArgumentException();
		} else if (day < 10) {
			return " " + day;
		} else {
			return String.valueOf(day);
		}
	}
}
