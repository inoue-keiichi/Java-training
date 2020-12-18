package ch06.ex08;

import java.util.Arrays;
import java.util.Random;

// 配列の要素数が300000くらいでArrays.parallelSortの方が早くなった。

//<Array Size is 10000>
//sort: 4[ms]
//parallelSort: 9[ms]
//
//<Array Size is 100000>
//sort: 12[ms]
//parallelSort: 29[ms]
//
//<Array Size is 300000>
//sort: 27[ms]
//parallelSort: 26[ms]
//
//<Array Size is 400000>
//sort: 33[ms]
//parallelSort: 9[ms]
//
//<Array Size is 500000>
//sort: 39[ms]
//parallelSort: 14[ms]
//
//<Array Size is 1000000>
//sort: 84[ms]
//parallelSort: 24[ms]

public class Main {
	public static void main(final String[] args) {
		calculateSortTime(10000);
		System.out.println();
		calculateSortTime(100000);
		System.out.println();
		calculateSortTime(300000);
		System.out.println();
		calculateSortTime(400000);
		System.out.println();
		calculateSortTime(500000);
		System.out.println();
		calculateSortTime(1000000);
	}

	private static void calculateSortTime(final int arraySize) {
		final Random random = new Random(System.currentTimeMillis());
		final long[] arr = new long[arraySize];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = random.nextLong();
		}
		final long[] arr2 = arr.clone();

		System.out.println("<Array Size is " + arr.length + ">");

		final long first = System.currentTimeMillis();
		Arrays.sort(arr);
		System.out.println(String.format("%12s: %d[ms]", "sort", System.currentTimeMillis() - first));

		final long first2 = System.currentTimeMillis();
		Arrays.parallelSort(arr2);
		System.out.println(String.format("%12s: %d[ms]", "parallelSort", System.currentTimeMillis() - first2));
	}
}
