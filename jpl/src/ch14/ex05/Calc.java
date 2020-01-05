package ch14.ex05;

public class Calc {
	private static int num;

	public static void add(int addend) {
		synchronized (Calc.class) {
			num = num + addend;
			System.out.println(Thread.currentThread().getName() + ": " + num);
		}
	}
	
	public void subtract(int subtrahend) {
		synchronized (Calc.class) {
			num = num - subtrahend;
			System.out.println(Thread.currentThread().getName() + ": " + num);
		}
	}
}
