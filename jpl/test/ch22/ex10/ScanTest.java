package ch22.ex10;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class ScanTest {
	@Test
	public void normalTest() throws FileNotFoundException {
		final FileReader fr = new FileReader("/Users/inoue-keiichi/git/Java-training/jpl/src/ch22/ex10/sample.txt");
		final List<String> actualResult = Scan.breakUpSource(fr);

		final List<String> expectedResult = new ArrayList<>();
		expectedResult.add("token");
		expectedResult.add("token2");
		expectedResult.add("token3");

		assertEquals(expectedResult.size(), actualResult.size());
		for (int i = 0; i < expectedResult.size(); i++) {
			assertEquals(expectedResult.get(i), actualResult.get(i));
		}
	}
}
