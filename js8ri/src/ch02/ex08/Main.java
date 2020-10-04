package ch02.ex08;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class Main {

	public static void main(final String[] args) {

	}

	public static <T> Stream<T> zip(final Stream<T> first, final Stream<T> second) {
		Stream<T> f = first;
		Stream<T> s = second;
		final List<T> list = new ArrayList<>();
		f = f.peek(e -> {
			list.add(e);
		});
		s = s.peek(e -> {
			list.add(e);
		});
		return Stream.of((T[]) list.toArray());
	}

}
