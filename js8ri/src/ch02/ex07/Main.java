package ch02.ex07;

import java.util.stream.Stream;

public class Main {

	public static void main(final String[] args) {
		final Stream stream = Stream.of(1, 2, 3, 4);
		stream.limit(4);
		stream.limit(4);
		stream.forEach(e -> {
		});
		stream.isParallel();
	}

	@SuppressWarnings("unchecked")
	public static <T> boolean isFinite(final Stream<T> stream) {

		try {
			stream.toArray();
		} catch (final IllegalStateException e) {
			return true;
		}
		return false;
	}
}
