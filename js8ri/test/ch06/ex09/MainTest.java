package ch06.ex09;

import static org.junit.Assert.assertArrayEquals;

import org.junit.jupiter.api.Test;

public class MainTest {
	@Test
	public void test() {
		final int[][] fibonacci = new int[][] { { 1, 1 }, { 1, 0 } };
		final Matrix[] fibos = Main.createArrays(fibonacci, 20);
		final int[] expected = { 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, 144, 233, 377, 610, 987, 1597, 2584, 4181, 6765,
				10946 };
		final int[] actual = new int[20];
		for (int i = 0; i < fibos.length; i++) {
			actual[i] = fibos[i].getMatrix()[0][0];
		}
		assertArrayEquals(expected, actual);
	}
}
