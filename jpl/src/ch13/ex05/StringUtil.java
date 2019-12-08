package ch13.ex05;

import java.util.regex.Pattern;

public class StringUtil {
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
