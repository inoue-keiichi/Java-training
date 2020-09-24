package dc1_2;

import java.awt.Button;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Panel;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Calendar;

class ClockView extends Frame implements Runnable {
	private static final ClockView clockView = new ClockView();
	private static final int MIN_LIMIT_FOR_WIDTH_BETWEEN_FRAME_AND_FONT = 10;
	private static final int MAX_LIMIT_FOR_WIDTH_BETWEEN_FRAME_AND_FONT = 20;
	private static final int MIN_LIMIT_FOR_HEIGHT_BETWEEN_FRAME_AND_FONT = 100;
	private static final int MAX_LIMIT_FOR_HEIGHT_BETWEEN_FRAME_AND_FONT = 0;

	private final ClockService clockService = ClockService.getInstance();
	private final MenuView menuView = MenuView.getInstance();
	static final Thread thread = new Thread(clockView);
	private final Panel menuPanel = new Panel();

	private ClockView() {
		setSize(500, 400);
		menuPanel.add(menuView.btn);
		menuPanel.setBackground(Color.DARK_GRAY);
		add(menuPanel, "South");

		addWindowListener(new ClockWindowAdapter());
	}

	public void paint(Graphics g) {
		// フレームの大きさによって文字の大きさを変える
		final Dimension size = getSize();
		FontMetrics fontMetrics = getFontMetrics(
				new Font(clockService.getFont(), Font.PLAIN, clockService.getFontSize()));
		while (size.width
				- fontMetrics.stringWidth(clockService.getTime()) < MIN_LIMIT_FOR_WIDTH_BETWEEN_FRAME_AND_FONT) {
			clockService.setFontSize(clockService.getFontSize() - 1);
			fontMetrics = getFontMetrics(new Font(clockService.getFont(), Font.PLAIN, clockService.getFontSize()));
		}
		while (size.width
				- fontMetrics.stringWidth(clockService.getTime()) > MAX_LIMIT_FOR_WIDTH_BETWEEN_FRAME_AND_FONT) {
			clockService.setFontSize(clockService.getFontSize() + 1);
			fontMetrics = getFontMetrics(new Font(clockService.getFont(), Font.PLAIN, clockService.getFontSize()));
		}
		this.setMinimumSize(new Dimension(MAX_LIMIT_FOR_HEIGHT_BETWEEN_FRAME_AND_FONT,
				fontMetrics.getHeight() + MIN_LIMIT_FOR_HEIGHT_BETWEEN_FRAME_AND_FONT));

		// ダブルバッファリング
		final Image back = createImage(size.width, size.height);
		final Graphics buffer = back.getGraphics();
		buffer.setColor(clockService.getFontColor());
		buffer.setFont(fontMetrics.getFont());
		buffer.drawString(clockService.getTime(),
				(int) (size.width - fontMetrics.stringWidth(clockService.getTime())) / 2,
				(int) (size.height + fontMetrics.getHeight()) / 2);
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
