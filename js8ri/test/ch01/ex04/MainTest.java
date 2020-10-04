package ch01.ex04;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.Test;

public class MainTest {
	@Test
	public void test() {
		final File dir = new File("./test/resource/dir");
		final File[] actual = dir.listFiles();
		Main.sortFiles(actual);
		final File[] expected = new File[6];
		expected[0] = new File("./test/resource/dir/dirA1");
		expected[1] = new File("./test/resource/dir/dirB1");
		expected[2] = new File("./test/resource/dir/readme.md");
		expected[3] = new File("./test/resource/dir/test1.txt");
		expected[4] = new File("./test/resource/dir/test2.txt");
		expected[5] = new File("./test/resource/dir/testtxt");
		assertArrayEquals(expected, actual);
	}
}
