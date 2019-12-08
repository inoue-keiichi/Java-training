package ch13.ex04;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.regex.Pattern;

public class StringUtil {
	public static void readTypeAndValue(String str) throws Exception {
		final List<Object> list = new ArrayList<>();
		String[] strArray = str.split("\n");
		String[] typeAndValue;

		try {
			for (String element : strArray) {
				typeAndValue = element.split(" ");
				Object obj;

				switch (typeAndValue[0]) {
				case "Boolean":
					obj = new Boolean(typeAndValue[1]);
					list.add(obj);
					break;
				case "Byte":
					obj = new Byte(typeAndValue[1]);
					list.add(obj);
					break;
				case "Character":
					char[] chrArray = typeAndValue[1].toCharArray();
					if (chrArray.length > 1) {
						throw new Exception();
					}
					obj = new Character(chrArray[0]);
					list.add(obj);
					break;
				case "Double":
					obj = new Double(typeAndValue[1]);
					list.add(obj);
					break;
				case "Float":
					obj = new Float(typeAndValue[1]);
					list.add(obj);
					break;
				case "Integer":
					obj = new Integer(typeAndValue[1]);
					list.add(obj);
					break;
				case "Short":
					obj = new Short(typeAndValue[1]);
					list.add(obj);
					break;
				case "Long":
					obj = new Long(typeAndValue[1]);
					list.add(obj);
					break;
				}
			}
			System.out.print(list.toString());
		} catch (Exception e) {
		}

	}

	public static String[] delimitedString(String from, char start, char end) {
		int startPos = from.indexOf(start);
		int endPos = from.lastIndexOf(end);
		int tmpStartPos;
		final List<String> matchStrList = new ArrayList<>();

		if (startPos == -1) {
			return null;
		} else if (endPos == -1) {
			matchStrList.add(from.substring(startPos));
			return matchStrList.toArray(new String[matchStrList.size()]);
		} else if (startPos > endPos) {
			return null;
		}
		while (startPos != -1) {
			while (endPos != -1) {
				matchStrList.add(from.substring(startPos, endPos + 1));
				endPos = from.substring(startPos, endPos).lastIndexOf(end);
			}
			endPos = from.lastIndexOf(end);
			tmpStartPos = from.substring(startPos + 1, endPos).indexOf(start);
			if (tmpStartPos == -1) {
				break;
			}
			startPos = startPos + tmpStartPos + 1;
		}
		return matchStrList.toArray(new String[matchStrList.size()]);
	}

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
