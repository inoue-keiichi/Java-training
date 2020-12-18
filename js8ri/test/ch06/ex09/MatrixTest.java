package ch06.ex09;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.fail;

import org.junit.jupiter.api.Test;

public class MatrixTest {

	@Test
	public void test() {
		final int[][] init1 = { { 1, 2 }, { 3, 4 } };
		final int[][] init2 = { { 1, 2 }, { 3, 4 } };
		final Matrix matrix1 = new Matrix(init1);
		final Matrix matrix2 = new Matrix(init2);
		final int[][] actual = matrix1.multiple(matrix2).getMatrix();
		assertThat(actual[0]).containsSequence(7, 10);
		assertThat(actual[1]).containsSequence(15, 22);
	}

	@Test
	public void test_error() {
		final int[][] init1 = { { 1, 2 }, { 1, 2 }, { 1, 2 } };
		try {
			new Matrix(init1);
			fail();
		} catch (final IllegalArgumentException e) {

		} catch (final Throwable e) {
			fail();
		}

		final int[][] init2 = { { 1, 2, 3 }, { 1, 2, 3 } };
		try {
			new Matrix(init2);
			fail();
		} catch (final IllegalArgumentException e) {

		} catch (final Throwable e) {
			fail();
		}
	}

}
