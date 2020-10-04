package ch01.ex05;

import java.awt.Frame;
import java.awt.Graphics;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Calendar;

// Runnableをラムダ式にして16行→13行になった。ネストが減ったので読みやすくなった。
public class Main {
	public static void main(final String[] args) {
		final Main main = new Main();
		main.new Clock();
	}

	class ClockRamda extends Frame {
		int h;
		int m;
		int s;
		Calendar now;

		public ClockRamda() {
			setSize(200, 200);

			this.addWindowListener(new WindowAdapter() {
				@Override
				public void windowClosing(final WindowEvent e) {
					System.exit(0);
				}
			});

			//同じ引数の抽象メソッドが複数あってラムダ式にできない。
			this.addWindowListener(new WindowAdapter() {
				@Override
				public void windowClosing(final WindowEvent e) {
					System.exit(0);
				}
			});

			final Thread thread = new Thread(() -> {
				while (true) {
					now = Calendar.getInstance();
					h = now.get(Calendar.HOUR_OF_DAY);
					m = now.get(Calendar.MINUTE);
					s = now.get(Calendar.SECOND);
					repaint();
					try {
						Thread.sleep(1000); // スリープ１秒
					} catch (final InterruptedException e) {
					}
				}
			});

			this.setVisible(true);
			thread.start();
		}

		@Override
		public void paint(final Graphics g) {
			g.drawString(h + ":" + m + ":" + s, 50, 100);
		}
	}

	class Clock extends Frame {
		int h;
		int m;
		int s;
		Calendar now;

		public Clock() {
			setSize(200, 200);

			this.addWindowListener(new WindowAdapter() {
				@Override
				public void windowClosing(final WindowEvent e) {
					System.exit(0);
				}
			});

			final Thread thread = new Thread(new Runnable() {
				@Override
				public void run() {
					while (true) {
						now = Calendar.getInstance();
						h = now.get(Calendar.HOUR_OF_DAY);
						m = now.get(Calendar.MINUTE);
						s = now.get(Calendar.SECOND);
						repaint();
						try {
							Thread.sleep(1000); // スリープ１秒
						} catch (final InterruptedException e) {
						}
					}
				}
			});
			this.setVisible(true);
			thread.start();
		}

		@Override
		public void paint(final Graphics g) {
			g.drawString(h + ":" + m + ":" + s, 50, 100);
		}
	}
}
