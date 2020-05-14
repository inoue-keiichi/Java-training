package ch22.ex14;

import java.util.StringTokenizer;

public class StringUtils {
	public static double sum(final String str) {
		final StringTokenizer stringTokenizer = new StringTokenizer(str, " ");
		double sum = 0;
		while (stringTokenizer.hasMoreTokens()) {
			sum += Double.parseDouble(stringTokenizer.nextToken());
		}
		return sum;
	}
}
