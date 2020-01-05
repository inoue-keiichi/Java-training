package ch14.ex03;

public class SynchronizedThread extends Thread {
	final Calc calc;
	final int addend;

	SynchronizedThread(final Calc calc, final int addend) {
		this.calc = calc;
		this.addend = addend;
	}

	public void run() {
		try {
			for (;;) {
				calc.add(addend);
				sleep(1000);
			}
		} catch (InterruptedException e) {
			return;
		}
	}

	public static void main(String[] args) {
		Calc calc = new Calc();
		new SynchronizedThread(calc, 1).start();
		new SynchronizedThread(calc, 1).start();
		new SynchronizedThread(calc, 1).start();
		new SynchronizedThread(calc, 1).start();
	}
}
