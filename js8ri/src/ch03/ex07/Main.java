package ch03.ex07;

import java.util.Comparator;

public class Main {

	public static Comparator<String> createComparator(final boolean normalOrder, final boolean distinguishCase,
			final boolean omitSpace) {
		return (final String o1, final String o2) -> {
			String str1 = omitSpace(o1, omitSpace);
			String str2 = omitSpace(o2, omitSpace);
			str1 = distinguishCase(str1, distinguishCase);
			str2 = distinguishCase(str2, distinguishCase);
			final char[] chr1 = str1.toCharArray();
			final char[] chr2 = str2.toCharArray();
			for (int i = 0; i < chr1.length && i < chr2.length; i++) {
				if (chr1[i] != chr2[i]) {
					return (chr1[i] - chr2[i]) * reverseSign(!normalOrder);
				}
			}
			return (o1.length() - o2.length()) * reverseSign(!normalOrder);
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
