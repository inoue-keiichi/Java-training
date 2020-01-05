package ch14.ex03;

public class Calc {
	private int num;

	public void add(int addend) {
		synchronized (Calc.class) {
			this.num = this.num + addend;
			System.out.println(Thread.currentThread().getName() + ": " + this.num);
		}
	}
}
