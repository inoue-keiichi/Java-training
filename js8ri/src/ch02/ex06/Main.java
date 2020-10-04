package ch02.ex06;

import java.util.stream.Stream;

public class Main {

	public static void main(final String[] args) {
		characterStream("boat").forEach(e -> System.out.println(String.valueOf(e)));

	}

	public static Stream<Character> characterStream(final String s) {
		return Stream.iterate(0, (x) -> x + 1).limit(s.length()).flatMap(i -> Stream.of(s.charAt(i)));
	}

}
