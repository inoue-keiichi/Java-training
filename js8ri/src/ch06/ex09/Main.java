package ch06.ex09;

import java.util.Arrays;

public class Main {
	final static int[][] fibonacci = new int[][] { { 1, 1 }, { 1, 0 } };

	public static Matrix[] createArrays(final int[][] element2X2, final int size) {
		final Matrix[] matrixs = new Matrix[size];
		Arrays.parallelSetAll(matrixs, (x) -> {
			return new Matrix(element2X2);
		});
		Arrays.parallelPrefix(matrixs, (x, y) -> {
			return x.multiple(y);
		});
		return matrixs;
	};

	public static void main(final String[] args) {
		final Matrix[] fibonacciArray = createArrays(fibonacci, 20);
		for (final Matrix fibo : fibonacciArray) {
			System.out.print(fibo.getMatrix()[0][0]);
			System.out.print(" ,");
		}
		System.out.print("...");
	}

}
