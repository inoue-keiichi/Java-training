package ch08.ex09;

import static org.junit.Assert.*;

import java.util.Scanner;

import org.junit.Test;

public class MainTest {

	@Test
	public void test_convert() {
		final Scanner scanner = new Scanner("hoge\nfuga\nfoo");
		final String[] expected = { "hoge", "fuga", "foo" };
		assertArrayEquals(expected, Main.convert(scanner).toArray());
	}

	@Test
	public void test_convertInt() {
		final Scanner scanner = new Scanner("4 5 6 7 8 9");
		final Integer[] expected = { 4, 5, 6, 7, 8, 9 };
		assertArrayEquals(expected, Main.convertInt(scanner).toArray());
	}
}
