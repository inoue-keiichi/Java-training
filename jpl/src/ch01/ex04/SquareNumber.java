package src.ch01.ex04;

public class SquareNumber {

	static final int MAX = 10;

	public static void main(String[] args) {
		int squareNumber;
		System.out.println("Square Number Array");
		System.out.println("-------------------");
		for (int i = 1; i <= MAX; i++) {
			squareNumber = i*i;
			System.out.println(squareNumber);
		}
	}
}
