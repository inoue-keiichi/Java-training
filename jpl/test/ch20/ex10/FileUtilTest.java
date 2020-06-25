package ch20.ex10;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;

import org.junit.Test;

public class FileUtilTest {
	@Test
	public void normalTest() throws IOException {
		String text = "hoge foo hogehoge 2 hoge var foo hoge foover hoge 2";
		File file = File.createTempFile("test", null);
		try (final FileOutputStream fos = new FileOutputStream(file)) {
			fos.write(text.getBytes());
		}

		Map<String, Integer> result = FileUtil.countTerms(file);
		assertTrue(result.containsKey("hoge"));
		assertEquals(4, (int) result.get("hoge"));
		assertTrue(result.containsKey("foo"));
		assertEquals(2, (int) result.get("foo"));
		assertTrue(result.containsKey("hogehoge"));
		assertEquals(1, (int) result.get("hogehoge"));
		assertTrue(result.containsKey("var"));
		assertEquals(1, (int) result.get("var"));
		assertTrue(result.containsKey("foover"));
		assertEquals(1, (int) result.get("foover"));
		assertTrue(result.containsKey("2"));
		assertEquals(2, (int) result.get("2"));
	}
}
