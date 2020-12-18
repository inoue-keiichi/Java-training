package ch01.ex03;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class MainTest {
	@Test
	public void test() {
		final String dir = "./test/resource/dir";
		final List<String> actual = Arrays.asList(Main.getFiles(dir, "txt"));
		assertThat(actual).contains("test1.txt");
		assertThat(actual).contains("test2.txt");
		assertThat(actual).doesNotContain("readme.md");
		assertThat(actual).doesNotContain("testtxt");
		assertEquals(2, actual.size());
	}

	@Test
	public void test_extensionIsNull() {
		final String dir = "./test/resource/dir";
		final List<String> actual = Arrays.asList(Main.getFiles(dir, null));
		assertThat(actual).contains("testtxt");
		assertEquals(1, actual.size());
	}

	@Test
	public void test_extensionIsEmpty() {
		final String dir = "./test/resource/dir";
		final List<String> actual = Arrays.asList(Main.getFiles(dir, ""));
		assertThat(actual).contains("testtxt");
		assertEquals(1, actual.size());
	}
}
