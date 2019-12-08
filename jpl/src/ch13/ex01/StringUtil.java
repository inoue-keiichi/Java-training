package ch13.ex01;

import java.util.Objects;

public class StringUtil {

	public static final int countChar(String str, char searchChr) {
		if (Objects.isNull(str)) {
			return -1;
		}

		final char[] chrs = str.toCharArray();
		int count = 0;
		for (char ch : chrs) {
			if (ch == searchChr) {
				count++;
			}
		}
		return count;
	}
}
