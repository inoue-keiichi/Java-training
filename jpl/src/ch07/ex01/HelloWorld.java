package ch07.ex01;

public class HelloWorld {
	public static void main(String[] args) {
		final char charH = '\u0048';
		final char charE = '\u0065';
		final char charL = '\u006c';
		final char charO = '\u006f';
		final char charKanma = '\u002c';
		final char charSpace = '\u0020';
		final char charW = '\u0057';
		final char charR = '\u0072';
		final char charD = '\u0064';

		System.out.println(String.format("%c%c%c%c%c%c%c%c%c%c%c%c",
				charH, charE,charL,charL,charO,charKanma,charSpace,charW,charO,charR,charL,charD));
	}
}
