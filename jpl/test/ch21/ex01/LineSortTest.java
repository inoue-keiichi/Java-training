package ch21.ex01;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class LineSortTest {
	@Test
	public void normalTest() {
		try {
			List<String> expectedResult = new ArrayList<>();
			expectedResult.add("Canada");
			expectedResult.add("China");
			expectedResult.add("German");
			expectedResult.add("Italia");
			expectedResult.add("Japan");
			expectedResult.add("Koria");
			List<String> actualResult = LineSort
					.createSortList("/Users/inoue-keiichi/git/Java-training/jpl/src/ch21/ex01/sample.txt");
			assertEquals(actualResult, expectedResult);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
