package ch14.ex05;

import java.awt.Component;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class SynchronizedThread {
	final int addend;

	SynchronizedThread(final int addend) {
		this.addend = addend;
		Runnable service = new Runnable() {
			@Override
			public void run() {
				for (;;) {
					Calc.add(addend);
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO 自動生成された catch ブロック
						e.printStackTrace();
					}
				}
			}
		};
		new Thread(service).start();
	}

	public static void main(String[] args) {
		new SynchronizedThread(1);
		new SynchronizedThread(1);
		new SynchronizedThread(1);
		new SynchronizedThread(1);
		Calc calc = new Calc();

		for (;;) {
			calc.subtract(4);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			}
		}
	}
}
