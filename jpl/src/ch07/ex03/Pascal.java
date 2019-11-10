package ch07.ex03;

import java.io.IOException;

public class Pascal {
	private final int[][] pascalMatrix;
	private final int rowNum;

	public Pascal(int rowNum) {
		this.rowNum = rowNum;
		pascalMatrix = createPascalMatrix();
	}
	
	public Pascal() {
		this(12);
	}

	/**
	 * 引数の長さと同じパスカルの三角形の行を生成します。
	 * 
	 * @param int[] パスカルの三角形の行
	 * @return int[] 一段下のパスカルの三角形の行
	 */
	private final int[] createNextPascalRow(final int[] pascalRow) {
		int[] tmpArray = new int[pascalRow.length + 2];
		tmpArray[0] = 0;
		tmpArray[tmpArray.length - 1] = 0;
		for (int i = 0; i < pascalRow.length; i++) {
			tmpArray[i + 1] = pascalRow[i];
		}
		int[] nextPascalRow = new int[pascalRow.length + 1];
		for (int i = 0; i < tmpArray.length - 1; i++) {
			nextPascalRow[i] = tmpArray[i] + tmpArray[i + 1];
		}
		return nextPascalRow;
	}

	/**
	 * 引数に渡した整数と同じ行数のパスカルの三角形を intの行列として返します。
	 * 
	 * @param int パスカルの三角形の行数
	 * @return int[][] パスカルの三角形
	 * @throws IllegalArgumentException
	 */
	private final int[][] createPascalMatrix() {
		if (rowNum < 1) {
			throw new IllegalArgumentException("引数は自然数のみ有効です。");
		}

		int[][] pascalMatrix = new int[rowNum][];
		pascalMatrix[0] = new int[1];
		pascalMatrix[0][0] = 1;

		if (rowNum == 1) {
			return pascalMatrix;
		}

		for (int i = 1; i < rowNum; i++) {
			pascalMatrix[i] = createNextPascalRow(pascalMatrix[i - 1]);
		}
		return pascalMatrix;
	}

	private final void ShowPascalRow(int rowNum) {
		System.out.print(String.format("%3d | { ", rowNum));
		for (int i = 0; i < pascalMatrix[rowNum - 1].length; i++) {
			System.out.print(pascalMatrix[rowNum - 1][i]);
			if (i < rowNum - 1) {
				System.out.print(" ,");
			}
		}
		System.out.println(" }");
	}

	public final void ShowPascalMatrix() {
		for (int i = 0; i < pascalMatrix.length; i++) {
			ShowPascalRow(i + 1);
		}
		System.out.println("");
	}
}
