package ch03.ex12;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class SimpleSortHarnessTest {

	@Test
	void test() {
		Object[] data = { 0.3, 1.3e-2, 7.9, 3.17 };
		Object[] expectedData = { 1.3e-2, 0.3, 3.17, 7.9 };
		SortHarness bsort = new SimpleSortHarness(data);
		SortMetrics metrics = bsort.sort();

		for (int i = 0; i < data.length; i++) {
			assertEquals(expectedData[i], data[i]);
		}

	}
}