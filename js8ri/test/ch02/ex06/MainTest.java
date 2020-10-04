package ch02.ex06;

import static org.junit.Assert.*;

import java.util.stream.Stream;

import org.junit.Test;

public class MainTest {

	@Test
	public void test() {
		final String str = "boat";
		final Stream<Character> actual = Main.characterStream(str);
		final Character[] array = actual.toArray(Character[]::new);
		assertEquals('b', array[0].charValue());
		assertEquals('o', array[1].charValue());
		assertEquals('a', array[2].charValue());
		assertEquals('t', array[3].charValue());
		assertEquals(4, array.length);
	}
}
