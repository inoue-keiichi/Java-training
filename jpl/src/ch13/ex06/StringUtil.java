package ch13.ex06;

import java.util.regex.Pattern;

import ch13.ex05.BadDataSetException;

public class StringUtil {
	public static final String separator(String str, int interval) throws BadDataSetException, OutOfStringIndexException {
		if (!Pattern.matches("[1-9]*", str)) {
			throw new BadDataSetException(str);
		} else if (str.length() <= interval) {
			throw new OutOfStringIndexException();
		}

		StringBuilder sb = new StringBuilder(str);
		int index = sb.length() - interval;
		while (index > 0) {
			sb.insert(index, ',');
			index = index - interval;
		}
		return sb.toString();
	}

	public static final String separator(String str) throws BadDataSetException {
		if (!Pattern.matches("[1-9]*", str)) {
			throw new BadDataSetException(str);
		}

		final int indexInterval = 3;
		StringBuilder sb = new StringBuilder(str);
		int index = sb.length() - indexInterval;
		while (index > 0) {
			sb.insert(index, ',');
			index = index - indexInterval;
		}
		return sb.toString();
	}
}
