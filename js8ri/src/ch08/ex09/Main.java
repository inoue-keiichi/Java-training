package ch08.ex09;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class Main {

	public static void main(final String[] args) throws FileNotFoundException {
		final Scanner scanner = new Scanner(new File("./src/resource/sample2.txt"));
		convert(scanner).forEach(System.out::println);

		System.out.println("-----------------");

		final Scanner scanner2 = new Scanner("4 5 6 7 8 9");
		convertInt(scanner2).forEach(System.out::println);
	}

	public static Stream<String> convert(final Scanner scanner) {
		final Iterator<String> iter = new Iterator<>() {
			String nextLine = null;

			@Override
			public boolean hasNext() {
				if (nextLine != null) {
					return true;
				} else {
					try {
						nextLine = scanner.nextLine();
					} catch (final NoSuchElementException e) {
						return false;
					}
					return (nextLine != null);
				}
			}

			@Override
			public String next() {
				if (nextLine != null || hasNext()) {
					final String line = nextLine;
					nextLine = null;
					return line;
				} else {
					throw new NoSuchElementException();
				}
			}
		};

		return StreamSupport.stream(Spliterators.spliteratorUnknownSize(
				iter, Spliterator.ORDERED | Spliterator.NONNULL), false);
	}

	public static Stream<Integer> convertInt(final Scanner scanner) {
		final Iterator<Integer> iter = new Iterator<>() {
			Integer nextInt = null;

			@Override
			public boolean hasNext() {
				if (nextInt != null) {
					return true;
				} else {
					try {
						nextInt = scanner.nextInt();
					} catch (final NoSuchElementException e) {
						return false;
					}
					return (nextInt != null);
				}
			}

			@Override
			public Integer next() {
				if (nextInt != null || hasNext()) {
					final Integer num = nextInt;
					nextInt = null;
					return num;
				} else {
					throw new NoSuchElementException();
				}
			}
		};

		return StreamSupport.stream(Spliterators.spliteratorUnknownSize(
				iter, Spliterator.ORDERED | Spliterator.NONNULL), false);
	}
}
