package ch02.ex05;

import java.util.stream.Stream;

public class Main {

	public static void main(final String[] args) {
		final Stream<Long> random = getRandomStream(500000L);
		random.limit(10).forEach(System.out::println);
	}

	public static Stream<Long> getRandomStream(final long seed) {
		final long a = 25214903917L;
		final long c = 11l;
		final long m = (long) Math.pow(2, 48);
		final Stream<Long> result = Stream.iterate(seed, (x) -> {
			return (a * x + c) % m;
		});
		return result;
	}

}
