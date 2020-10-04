package ch02.ex03;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//StreamとParallelStreamそれぞれの結果
//        Stream:    47880231[ns]
//ParallelStream:     3485503[ns]
public class Main {
	public static void main(final String[] args) {
		final String str = "I hope you do not mind my contacting you out of red. "
				+ "My name is Yoko Ito of the financial division at ABC Corporation. "
				+ "I came across your website and found your new accounting service XXX very interesting.";
		countWords(str, 4);
		countWordsParallel(str, 4);
	}

	private static List<String> decomposeToWords(final String str) {
		final List<String> result = new ArrayList<>();
		try (final Scanner in = new Scanner(str)) {
			in.useDelimiter(" |\\. ");
			while (in.hasNext()) {
				result.add(in.next());
			}
		}
		return result;
	}

	public static long countWords(final String str, final int length) {
		final List<String> list = decomposeToWords(str);
		final long startTime = System.nanoTime();
		final long result = list.stream().filter(e -> e.length() >= length).count();
		final long endTime = System.nanoTime();
		System.out.println(String.format("%16s%15s", "Stream: ", (endTime - startTime) + "[ns]"));
		return result;
	}

	public static long countWordsParallel(final String str, final int length) {
		final List<String> list = decomposeToWords(str);
		final long startTime = System.nanoTime();
		final long result = list.parallelStream().filter(e -> e.length() >= length).count();
		final long endTime = System.nanoTime();
		System.out.println(String.format("%16s%15s", "ParallelStream: ", (endTime - startTime) + "[ns]"));
		return result;
	}

}
