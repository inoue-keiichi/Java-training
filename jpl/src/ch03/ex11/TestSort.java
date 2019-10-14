package ch03.ex11;

public class TestSort {
	static double[] testData = { 0.3, 1.3e-2, 7.9, 3.17 };

	public static void main(String[] args) {
		
		SortDouble bsort = new SimpleSortDouble(testData);
		SortMetrics metrics = bsort.sort();
		System.out.println("Metrics: " + metrics);
		for (int i = 0; i < testData.length; i++) {
			System.out.println("¥t" + testData[i]);
		}
	}
}
