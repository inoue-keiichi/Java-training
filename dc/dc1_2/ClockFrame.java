package dc1_2;

import java.awt.Button;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Panel;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Calendar;

class ClockFrame extends Panel implements Runnable {
	protected static Color fontColor = Color.green;
	protected static Color backgroundColor = Color.black;
	protected static int fontSize = 100;
	protected static int font = Font.PLAIN;

	protected static int h;
	protected static int m;
	protected static int s;
	protected static Calendar now;
	static final ClockFrame clock = new ClockFrame();
	static final Thread thread = new Thread(clock);

	public void paint(Graphics g) {
		Dimension size = getSize();
		Image back = createImage(size.width, size.height);
		Graphics buffer = back.getGraphics();

		buffer.setColor(fontColor);
		buffer.setFont(new Font("Dialog", font, fontSize));
		buffer.drawString(h + ":" + m + ":" + s, 0, 200);
		g.drawImage(back, 0, 0, this);
	}

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
			} catch (InterruptedException e) {
			}
		}
	}
}
