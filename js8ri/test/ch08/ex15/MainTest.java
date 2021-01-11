package ch08.ex15;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;

public class MainTest {
	@Test
	public void test_file() throws IOException {
		final Map<String, List<String>> actual = Main.findLines("./test/resource/text/text/term1.txt", "red");
		assertAll(() -> assertThat(actual.get("./test/resource/text/text/term1.txt"))
				.containsOnly("red blue green yellow orange gold silver", "red red red red red"));
	}

	@Test
	public void test_no_line() throws IOException {
		final Map<String, List<String>> actual = Main.findLines("./test/resource/text/text/term1.txt", "hoge");
		assertEquals(0, actual.size());
	}

	@Test
	public void test_folder() throws IOException {
		final Map<String, List<String>> actual = Main.findLines("./test/resource/text", "red");
		assertAll(() -> assertThat(actual.get("./test/resource/text/text/term1.txt"))
				.containsOnly("red blue green yellow orange gold silver", "red red red red red"),
				() -> assertThat(actual.get("./test/resource/text/term1.txt"))
						.containsOnly("red blue green yellow orange gold silver"),
				() -> assertThat(actual.get("./test/resource/text/term3.txt"))
						.containsOnly("red blue green yellow orange gold silver"));
	}
}
