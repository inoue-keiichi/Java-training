import java.awt.Container;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Calendar;

public class dc1_1 {
	public static void main(String[] args) {
		Clock.clock.setVisible(true);
		Clock.thread.start();
	}
}

class Clock extends Frame implements Runnable {
	protected static int h;
	protected static int m;
	protected static int s;
	protected static Calendar now;
	static Clock clock = new Clock();
	static Thread thread = new Thread(clock);

	public Clock() {
		setSize(200, 200);
		addWindowListener(new MyWindowAdapter());
	}

	public void paint(Graphics g) {
		g.drawString(h + ":" + m + ":" + s, 50, 50);
	}

	@Override
	public void run() {
		while (true) {
			now = Calendar.getInstance();
			h = now.get(Calendar.HOUR_OF_DAY);
			m = now.get(Calendar.MINUTE);
			s = now.get(Calendar.SECOND);
			System.out.println(h + ":" + m + ":" + s);
			repaint();
			try {
				Thread.sleep(1000); // スリープ１秒
			} catch (InterruptedException e) {
			}
		}
	}
}

class MyWindowAdapter extends WindowAdapter {
	public void windowClosing(WindowEvent e) {
		System.exit(0);
	}
}