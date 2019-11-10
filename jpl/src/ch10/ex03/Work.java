package ch10.ex03;

import java.io.IOException;

import ch06.ex01.Week;

public class Work {

	public static boolean isWorkDay(Week week) throws IllegalStateException {
		switch (week) {
		case SUNDAY:
			return false;
		case MONDAY:
			return true;
		case TUESDAY:
			return true;
		case WEDNESDAY:
			return true;
		case THUSDAY:
			return true;
		case FRIDAY:
			return true;
		case SATUDAY:
			return false;
		default:
			throw new IllegalStateException();
		}
	}

}
