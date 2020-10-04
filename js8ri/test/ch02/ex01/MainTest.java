package ch02.ex01;

import static org.junit.Assert.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class MainTest {

	@Test
	public void test() {
		try {
			final String contents = new String(Files.readAllBytes(Paths.get("./src/resource/sample1.txt")),
					StandardCharsets.UTF_8);
			final List<String> words = Arrays.asList(contents.split("[\\P{L}]+"));
			final int actual = Main.countWords(words);
			assertEquals(9, actual);
		} catch (final IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
	}
}
