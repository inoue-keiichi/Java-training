package ch01.ex13;

public class ImprovedFibonacci {

	static final int MAX_INDEX = 9;

	public static void main(String[] args) {
		int lo = 1;
		int hi = 1;
		String mark = "";
		boolean isEven = false;

		System.out.printf("1: %d %n", lo);
		for (int i = 2; i <= MAX_INDEX; i++) {
			isEven = hi % 2 == 0 ? true : false;
			if (isEven)
				mark = " *";
			else
				mark = "";
			System.out.printf("%d: %d %s %n", i, hi, mark);
			hi = lo + hi;
			lo = hi - lo;
		}
	}
}
