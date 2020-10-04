package ch01.ex03;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class MainTest {
	@Test
	public void test() {
		final String dir = "./test/resource/dir";
		final List<String> actual = Arrays.asList(Main.getFiles(dir, "txt"));
		assertThat(actual, hasItem("test1.txt"));
		assertThat(actual, hasItem("test2.txt"));
		assertThat(actual, not(hasItem("readme.md")));
		assertThat(actual, not(hasItem("testtxt")));
		assertEquals(2, actual.size());
	}

	@Test
	public void test_extensionIsNull() {
		final String dir = "./test/resource/dir";
		final List<String> actual = Arrays.asList(Main.getFiles(dir, null));
		assertThat(actual, hasItem("testtxt"));
		assertEquals(1, actual.size());
	}

	@Test
	public void test_extensionIsEmpty() {
		final String dir = "./test/resource/dir";
		final List<String> actual = Arrays.asList(Main.getFiles(dir, ""));
		assertThat(actual, hasItem("testtxt"));
		assertEquals(1, actual.size());
	}
}
