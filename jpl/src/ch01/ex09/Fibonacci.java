package src.ch01.ex09;

public class Fibonacci {
	
	static final int MAX_INDEX = 9;
	
	public static void main(String[] args) {
		int lo = 1;
		int hi = 1;

		int[] fibos = new int[MAX_INDEX];
		fibos[0] = lo;
		for (int i = 1; hi < 50; i++) {
			fibos[i] = hi;
			hi = lo + hi;
			lo = hi - lo;
		}

		for (int i = 0; i < MAX_INDEX; i++) {
			System.out.println(fibos[i]);
		}
	}
}
