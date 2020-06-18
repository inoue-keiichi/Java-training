package ch24.ex03;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class DateFormatter {
	public static Map<String, String> convertDate(final String date) {
		final DateFormat fullFormat = DateFormat.getDateInstance(DateFormat.FULL);
		final DateFormat longFormat = DateFormat.getDateInstance(DateFormat.LONG);
		final DateFormat mediumFormat = DateFormat.getDateInstance(DateFormat.MEDIUM);
		final DateFormat shortFormat = DateFormat.getDateInstance(DateFormat.SHORT);

		boolean isFull = true;
		boolean isLong = true;
		boolean isMedium = true;
		boolean isShort = true;

		final Map<String, String> formatMap = new HashMap<>();

		try {
			Date parsedDate = fullFormat.parse(date);
			formatMap.put("full", fullFormat.format(parsedDate));
		} catch (ParseException e) {
			isFull = false;
		}
		try {
			Date parsedDate = longFormat.parse(date);
			formatMap.put("long", longFormat.format(parsedDate));
		} catch (ParseException e) {
			isLong = false;
		}
		try {
			Date parsedDate = mediumFormat.parse(date);
			formatMap.put("medium", mediumFormat.format(parsedDate));
		} catch (ParseException e) {
			isMedium = false;
		}
		try {
			Date parsedDate = shortFormat.parse(date);
			formatMap.put("short", shortFormat.format(parsedDate));
		} catch (ParseException e) {
			isShort = false;
		}

		if (!isFull && !isLong && !isMedium && !isShort) {
			throw new IllegalArgumentException(date);
		}

		return formatMap;
	}

	public static void main(String[] args) {
		String date = "2020/02/02";
		System.out.println(date);
		Map<String, String> formatMap = convertDate(date);
		for (Entry<String, String> entry : formatMap.entrySet()) {
			System.out.println(entry.getKey() + " : " + entry.getValue());
		}
		System.out.println("");

		date = "2020年2月2日";
		System.out.println(date);
		Map<String, String> formatMap2 = convertDate(date);
		for (Entry<String, String> entry : formatMap2.entrySet()) {
			System.out.println(entry.getKey() + " : " + entry.getValue());
		}
		System.out.println("");

		date = "20/02/02";
		System.out.println(date);
		Map<String, String> formatMap3 = convertDate(date);
		for (Entry<String, String> entry : formatMap3.entrySet()) {
			System.out.println(entry.getKey() + " : " + entry.getValue());
		}
		System.out.println("");

		date = "20/2/2";
		System.out.println(date);
		Map<String, String> formatMap5 = convertDate(date);
		for (Entry<String, String> entry : formatMap5.entrySet()) {
			System.out.println(entry.getKey() + " : " + entry.getValue());
		}
		System.out.println("");

		//		date = "2020-02-02";
		//		System.out.println(date);
		//		Map<String, String> formatMap4 = convertDate(date);
		//		for (Entry<String, String> entry : formatMap4.entrySet()) {
		//			System.out.println(entry.getKey() + " : " + entry.getValue());
		//		}
		//		System.out.println("");

	}
}
