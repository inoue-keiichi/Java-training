package ch02.ex08;

import static org.junit.Assert.*;

import java.util.stream.Stream;

import org.junit.Test;

public class MainTest {

	@Test
	public void test() {
		final Stream<String> first = Stream.of("Hokkaido", "Miyagi", "Kanagawa", "Gifu");
		final Stream<String> second = Stream.of("Sapporo", "Sendai", "Yokohama");
		final String[] actual = Main.zip(first, second).toArray(String[]::new);
		final String[] expected = { "Hokkaido", "Sapporo", "Miyagi", "Sendai", "Kanagawa", "Yokohama" };
		assertArrayEquals(expected, actual);
	}
}
