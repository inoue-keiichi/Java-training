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

class ClockView extends Panel implements Runnable {
	private static final ClockView clockView = new ClockView();
	private final ClockService clockService = ClockService.getInstance();
	static final Thread thread = new Thread(clockView);

	public void paint(Graphics g) {
		Dimension size = getSize();
		Image back = createImage(size.width, size.height);
		Graphics buffer = back.getGraphics();
		
		buffer.setColor(clockService.getFontColor());
		buffer.setFont(new Font("Dialog", clockService.getFont(), clockService.getFontSize()));
		buffer.drawString(ClockService.getInstance().getHour() + ":" + ClockService.getInstance().getMinute() + ":"
				+ ClockService.getInstance().getSecond(), 0, 200);
		g.drawImage(back, 0, 0, this);
	}

	@Override
	public void run() {
		while (true) {
			clockService.setHour(Calendar.getInstance().get(Calendar.HOUR_OF_DAY));
			clockService.setMinute(Calendar.getInstance().get(Calendar.MINUTE));
			clockService.setSecond(Calendar.getInstance().get(Calendar.SECOND));
			repaint();
			try {
				Thread.sleep(1000); // スリープ１秒
			} catch (InterruptedException e) {
			}
		}
	}

	public static final ClockView getInstance() {
		return clockView;
	}
}
