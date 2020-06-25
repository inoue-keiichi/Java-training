package ch20.ex09;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.Map;
import java.util.Objects;

import org.junit.Test;

public class FileUtilTest {

	@Test
	public void normalTest() {
		try {
			Map<String, String> result = FileUtil
					.getFileInfo("/Users/inoue-keiichi/git/Java-training/jpl/test/resources/sample.txt");
			assertTrue(result.containsKey("name"));
			assertTrue(result.containsKey("path"));
			assertTrue(result.containsKey("absolutePath"));
			assertTrue(result.containsKey("canonicalPath"));
			assertTrue(result.containsKey("parentPath"));
			assertTrue(result.containsKey("freeSpace"));
			assertTrue(result.containsKey("totalSpace"));
			assertTrue(result.containsKey("usableSpace"));
			assertTrue(Objects.nonNull(result.get("name")));
			assertTrue(Objects.nonNull(result.get("path")));
			assertTrue(Objects.nonNull(result.get("absolutePath")));
			assertTrue(Objects.nonNull(result.get("canonicalPath")));
			assertTrue(Objects.nonNull(result.get("parentPath")));
			assertTrue(Objects.nonNull(result.get("freeSpace")));
			assertTrue(Objects.nonNull(result.get("totalSpace")));
			assertTrue(Objects.nonNull(result.get("usableSpace")));
		} catch (IOException e) {
			fail();
		}
	}
}
