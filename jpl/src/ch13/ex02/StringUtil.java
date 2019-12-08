package ch13.ex02;

import java.util.Objects;

public class StringUtil {
	public static final int countChar(String str, char searchChr) {
		if(Objects.isNull(str)) {
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

	public static final int countString(String str, String searchStr) {
		int count = 0;
		int fromIndex = 0;

		if (Objects.isNull(str) || Objects.isNull(searchStr)) {
			return -1;
		} else if (str.isEmpty() || searchStr.isEmpty()) {
			return -1;
		}

		while (str.indexOf(searchStr, fromIndex) != -1) {
			fromIndex = str.indexOf(searchStr, fromIndex) + searchStr.length();
			count++;
		}
		return count;
	}
}
