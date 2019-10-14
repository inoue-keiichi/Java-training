package ch04.ex02;

import ch03.ex08.Vehicle;

public class TestSortVehicle {
	static final Vehicle car1 = new Vehicle("A");
	static final Vehicle car2 = new Vehicle("B");
	static final Vehicle car3 = new Vehicle("C");
	
	
	static Vehicle[] testData = {car3, car1,  car2};

	public static void main(String[] args) {
		
		SortHarness bsort = new SortVehicle(testData);
		SortMetrics metrics = bsort.sort();
		System.out.println("Metrics: " + metrics);
		for (int i = 0; i < testData.length; i++) {
			System.out.println("\t" + testData[i]);
		}
	}
}
