package ch22.ex01;

import java.util.Random;

public class ArrayTable {
	public static int ROW_LENGTH = 80;

	public static void main(String[] args) {
		final float[] array = new float[85];
		final Random random = new Random();
		for (int i = 0; i < array.length; i++) {
			array[i] = random.nextFloat();
		}
		array[80] = (float) 1131.34777;
		array[81] = (float) 1;
		array[82] = (float) 11.3;
		array[83] = (float) 1131.3477733;
		array[84] = (float) 322432344;
		ArrayTable.displayArray(array, 1);
	}

	public static void displayArray(final float[] array, final int columnNum) {
		int maxLen = 0;
		for (float element : array) {
			if (maxLen < String.format("%f", element).length()) {
				maxLen = String.format("%f", element).length();
			}
		}
		final String format = "%" + maxLen + "f";
		String row = new String();
		for (float element : array) {
			row += "| ";
			row += String.format(format, element);
			if (row.length() > ROW_LENGTH) {
				System.out.println(row);
				row = new String();
			}
		}
		System.out.println(row);
	}
}
