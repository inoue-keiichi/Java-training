package ch02.ex12;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

public class Main {

	public static void main(final String[] args) {
		final String[] array = { "", "I", "my", "me", "mine", "you", "your", "you", "yours", "Hokkaido", "Hakodate",
				"Sapporo", "Karafuto", "Shikotanhanto", "Ishigakizima", "Ogasawarasyoto", "Miyakozima" };
		final int[] shortsWords = countShorterWordsThan(20, Stream.of(array));
		System.out.println(Arrays.toString(shortsWords));
	}

	public static int[] countShorterWordsThan(final int length, final Stream<String> stream) {
		final AtomicInteger[] shortWords = new AtomicInteger[length];
		for (int i = 0; i < shortWords.length; i++) {
			shortWords[i] = new AtomicInteger();
		}

		stream.parallel().forEach(s -> {
			if (s.length() < length) {
				shortWords[s.length()].getAndIncrement();
			}
		});
		return Stream.of(shortWords).mapToInt(e -> e.get()).toArray();
	}

}
