package ch09.ex06;

import static org.junit.Assert.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import org.junit.jupiter.api.Test;

public class MainTest {
	@Test
	public void test() throws IOException {
		try {
			Main.writeReversely(Path.of("./test/resource/io/in1.txt"), Path.of("./test/resource/io/out.txt"));
		} catch (IOException e) {
			fail();
		}

		String[] actual = Files.lines(Path.of("./test/resource/io/out.txt")).toArray(String[]::new);
		String[] expected = Files.lines(Path.of("./test/resource/io/expected/out3.txt")).toArray(String[]::new);
		assertArrayEquals(expected, actual);
	}

	@Test
	public void test_empty() throws IOException {
		try {
			Main.writeReversely(Path.of("./test/resource/io/in2.txt"), Path.of("./test/resource/io/out.txt"));
		} catch (IOException e) {
			fail();
		}

		String[] actual = Files.lines(Path.of("./test/resource/io/out.txt")).toArray(String[]::new);
		String[] expected = Files.lines(Path.of("./test/resource/io/expected/out2.txt")).toArray(String[]::new);
		assertArrayEquals(expected, actual);
	}
}
