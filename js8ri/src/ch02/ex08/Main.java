package ch02.ex08;

import java.util.Iterator;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class Main {

	public static void main(final String[] args) {
		final Stream<String> first = Stream.of("Hokkaido", "Miyagi", "Kanagawa", "Gifu");
		final Stream<String> second = Stream.of("Sapporo", "Sendai", "Yokohama");
		zip(first, second).forEach(System.out::println);
	}

	public static <T> Stream<T> zip(final Stream<T> first, final Stream<T> second) {

		final Iterator<T> i1 = first.iterator();
		final Iterator<T> i2 = second.iterator();
		final Iterator<T> iter = new Iterator<T>() {
			boolean i1Flag = true;

			@Override
			public boolean hasNext() {
				return i1.hasNext() && i2.hasNext();
			}

			@Override
			public T next() {
				if (i1Flag) {
					i1Flag = false;
					return i1.next();
				}
				i1Flag = true;
				return i2.next();
			}
		};

		final Spliterator<T> spliter = Spliterators.spliteratorUnknownSize(iter, Spliterator.ORDERED);
		return StreamSupport.stream(spliter, false);
	}

}
