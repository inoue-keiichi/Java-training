package ch04.ex02;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import ch03.ex08.Vehicle;

public class SortVehicleTest {
	@Test
	void test() {
		final Vehicle car1 = new Vehicle("A");
		final Vehicle car2 = new Vehicle("B");
		final Vehicle car3 = new Vehicle("C");
		final Vehicle car4 = new Vehicle("D");

		Vehicle[] testData = { car3, car4, car1, car2 };
		Vehicle[]expectedData = { car1, car2, car3, car4, };

		SortHarness bsort = new SortVehicle(testData);
		SortMetrics metrics = bsort.sort();

		for (int i = 0; i < testData.length; i++) {
			assertEquals(expectedData[i], testData[i]);
		}

	}
}
