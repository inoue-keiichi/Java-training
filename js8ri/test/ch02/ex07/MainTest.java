package ch02.ex07;

import static org.junit.Assert.*;

import java.util.stream.Stream;

import org.junit.Test;

import ch02.ex07.Main;

public class MainTest {

	@Test
	public void test() {
		final Integer[] array = { 1, 4, 9, 16 };
		final Stream<Integer> stream = Stream.of(array);
		assertEquals(false, Main.isFinite(stream));
		stream.forEach(e -> {
		});
		assertEquals(true, Main.isFinite(stream));
	}
}
