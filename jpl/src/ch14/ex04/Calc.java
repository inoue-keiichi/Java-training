package ch14.ex04;

public class Calc {
	private static int num;

	public static void add(int addend) {
		synchronized (Calc.class) {
			num = num + addend;
			System.out.println(Thread.currentThread().getName() + ": " + num);
		}
	}
}
