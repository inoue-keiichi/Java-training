package ch02.ex13;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {

	public static void main(final String[] args) {
		final String[] array = { "", "I", "my", "me", "mine", "you", "your", "you", "yours", "Hokkaido", "Hakodate",
				"Sapporo", "Karafuto", "Shikotanhanto", "Ishigakizima", "Ogasawarasyoto", "Miyakozima" };
		final int[] shortsWords = countShorterWordsThan(20, Stream.of(array));
		System.out.println(Arrays.toString(shortsWords));
	}

	public static int[] countShorterWordsThan(final int length, final Stream<String> stream) {
		final int[] result = new int[length];
		final Map<Integer, Long> shortsWordsCounts = stream.parallel()
				.collect(Collectors.groupingBy(w -> w.length(), Collectors.counting()));
		shortsWordsCounts.forEach((key, value) -> {
			result[key] = value.intValue();
		});
		return result;
	}
}
