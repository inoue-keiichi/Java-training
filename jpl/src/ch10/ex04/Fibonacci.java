package ch10.ex04;

public class Fibonacci {

	static final int MAX_INDEX = 9;

	public static void main(String[] args) {
		int lo = 1;
		int hi = 1;

		int[] fibos = new int[MAX_INDEX];
		fibos[0] = lo;

		int i = 0;
		while (hi < 50) {
			i++;
			fibos[i] = hi;
			hi = lo + hi;
			lo = hi - lo;
		}

		i = 0;
		while (i < MAX_INDEX) {
			System.out.println(fibos[i]);
			i++;
		}
	}
}
