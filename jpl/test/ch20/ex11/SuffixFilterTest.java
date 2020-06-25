package ch20.ex11;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FilenameFilter;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class SuffixFilterTest {
	@Test
	public void normalTest() {
		final FilenameFilter suffixFilter = new SuffixFilter("txt");
		final File dir = new File("/Users/inoue-keiichi/git/Java-training/jpl/test/resources/dirTest");
		final String[] fileNames = dir.list(suffixFilter);
		final List<String> result = Arrays.asList(fileNames);
		assertTrue(result.contains("sample1.txt"));
		assertTrue(result.contains("sample2.txt"));
		assertTrue(!result.contains("sample3.csv"));
		assertTrue(!result.contains("sample4.md"));
		assertTrue(!result.contains("testtxt"));
	}
}
