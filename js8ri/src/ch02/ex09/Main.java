package ch02.ex09;

import java.util.ArrayList;
import java.util.Optional;
import java.util.stream.Stream;

public class Main {

	public static void main(final String[] args) {

	}

	public static <T> ArrayList<T> combineWith(final Stream<ArrayList<T>> stream) {
		return stream.reduce(new ArrayList<>(), (total, list) -> {
			total.addAll(list);
			return total;
		});
	}

	public static <T> ArrayList<T> combineWith2(final Stream<ArrayList<T>> stream) {
		return stream.reduce(new ArrayList<>(), (total, list) -> {
			total.addAll(list);
			return total;
		}, (total1, total2) -> {
			total1.addAll(total2);
			return total1;
		});
	}

	public static <T> ArrayList<T> combineWith3(final Stream<ArrayList<T>> stream) {
		final Optional<ArrayList<T>> optional = stream.reduce((total, list) -> {
			total.addAll(list);
			return total;
		});
		return optional.orElse(null);
	}
}
