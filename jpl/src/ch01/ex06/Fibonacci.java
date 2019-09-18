package src.ch01.ex06;

public class Fibonacci {

	static final String TITLE = "Fibonacci Array";
	static final String UNDERBAR = "---------------";

	public static void main(String[] args) {
		int lo = 1;
		int hi = 1;
		System.out.println(TITLE);
		System.out.println(UNDERBAR);
		System.out.println(lo);
		while (hi < 50) {
			System.out.println(hi);
			hi = lo + hi;
			lo = hi - lo;
		}
	}
}
