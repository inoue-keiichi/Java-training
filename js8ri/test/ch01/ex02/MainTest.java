package ch01.ex02;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

public class MainTest {

	@Test
	public void test() {
		final String dir = "./test/resource/dir";
		final List<File> actual = Arrays.asList(Main.getSubDirectories(dir));
		final File expected1 = new File("./test/resource/dir/dirA1");
		final File expected2 = new File("./test/resource/dir/dirB1");
		final File expected3 = new File("./test/resource/dir/dirB1/dirB2");
		final File expected4 = new File("./test/resource/dir/dirB1/dirB2/dirB3");
		assertThat(actual).contains(expected1);
		assertThat(actual).contains(expected2);
		assertThat(actual).contains(expected3);
		assertThat(actual).contains(expected4);
		assertEquals(4, actual.size());
	}

	@Test
	public void test2() {
		final String dir = "./test/resource/dir";
		final List<File> actual = Arrays.asList(Main.getSubDirectories2(dir));
		final File expected1 = new File("./test/resource/dir/dirA1");
		final File expected2 = new File("./test/resource/dir/dirB1");
		final File expected3 = new File("./test/resource/dir/dirB1/dirB2");
		final File expected4 = new File("./test/resource/dir/dirB1/dirB2/dirB3");
		assertThat(actual).contains(expected1);
		assertThat(actual).contains(expected2);
		assertThat(actual).contains(expected3);
		assertThat(actual).contains(expected4);
		assertEquals(4, actual.size());
	}
}
