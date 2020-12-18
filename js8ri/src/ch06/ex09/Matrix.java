package ch06.ex09;

public class Matrix {
	int[][] matrix;

	public Matrix(final int[][] matrix) {
		if (!(matrix.length == 2 && matrix[0].length == 2)) {
			throw new IllegalArgumentException();
		}
		this.matrix = matrix;
	}

	public Matrix multiple(final Matrix matrix) {
		final int[][] result = new int[2][2];
		for (int j = 0; j < this.matrix[0].length; j++) {
			for (int i = 0; i < this.matrix.length; i++) {
				result[j][i] = this.matrix[0][i] * matrix.getMatrix()[j][0]
						+ this.matrix[1][i] * matrix.getMatrix()[j][1];
			}
		}
		return new Matrix(result);
	}

	public int[][] getMatrix() {
		return matrix;
	}
}
