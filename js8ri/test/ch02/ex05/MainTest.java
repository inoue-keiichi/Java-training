package ch02.ex05;

import static org.junit.Assert.*;

import java.util.stream.Stream;

import org.junit.Test;

public class MainTest {
	@Test
	public void test() {
		final Stream<Long> stream = Main.getRandomStream(500);
		final Long[] actual = stream.limit(100).toArray(Long[]::new);
		assertEquals(100, actual.length);
		for (final Long element : actual) {
			assertEquals("java.lang.Long", element.getClass().getName());
		}

	}

}
