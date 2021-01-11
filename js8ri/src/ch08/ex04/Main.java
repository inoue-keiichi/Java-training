package ch08.ex04;

import java.util.Comparator;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Stream;

public class Main {

	// min seed is 881498
	public static void main(final String[] args) {
		final long a = 11L;
		final long v = 246154705703781L;
		final long N = 0xffffffffffffL;
		final long m = 25214903917L;

		final Optional<Long> minSeed = Stream.iterate(0L, s -> (s - a) * v & N).map(s -> s ^ m).limit(1000000)
				.min(Comparator.naturalOrder());
		System.out.println("min seed is " + minSeed.get());
		System.out.println("--------------------");

		final Random generator = new Random(minSeed.get());
		final int[] count = { 0 };
		Stream.generate(() -> generator.nextDouble()).limit(1000000L).forEach(s -> {
			count[0] = count[0] + 1;
			if (s == 0) {
				System.out.println(String.format("I called nextDouble %d times", count[0]));
				return;
			}
		});
	}

}
