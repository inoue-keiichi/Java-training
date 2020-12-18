package ch03.ex07;

import java.util.Comparator;

public class Main {

	public static Comparator<String> createComparator(final boolean normalOrder, final boolean distinguishedCase,
			final boolean omittedSpace) {
		return (final String o1, final String o2) -> {
			String str1 = omitSpace(o1, omittedSpace);
			String str2 = omitSpace(o2, omittedSpace);
			str1 = distinguishCase(str1, distinguishedCase);
			str2 = distinguishCase(str2, distinguishedCase);
			return str1.compareTo(str2) * reverseSign(!normalOrder);
		};
	}

	private static String omitSpace(final String str, final boolean omit) {
		return omit ? str.replaceAll(" ", "") : str;
	}

	private static String distinguishCase(final String str, final boolean distinguish) {
		return distinguish ? str : str.toLowerCase();
	}

	private static int reverseSign(final boolean reverse) {
		if (reverse) {
			return -1;
		}
		return 1;
	}
}
