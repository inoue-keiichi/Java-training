package dc1_4;

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
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.Calendar;
import java.util.Objects;

import static dc1_4.ClockService.FrameKey;

class ClockView extends Frame implements Runnable, ComponentListener {
	private static final ClockView clockView = new ClockView();
	private static final int MIN_LIMIT_FOR_WIDTH_BETWEEN_FRAME_AND_FONT = 10;
	private static final int MAX_LIMIT_FOR_WIDTH_BETWEEN_FRAME_AND_FONT = 20;
//	private static final int MIN_LIMIT_FOR_HEIGHT_BETWEEN_FRAME_AND_FONT = 0;
//	private static final int MAX_LIMIT_FOR_HEIGHT_BETWEEN_FRAME_AND_FONT = 100;

	private final ClockService clockService = ClockService.getInstance();
	private final MenuView menuView = MenuView.getInstance();
	static final Thread thread = new Thread(clockView);
	private final Panel menuPanel = new Panel();
	private boolean resizedFlg = false;

	private ClockView() {
		try {
			this.setBounds(clockService.load(FrameKey.FRAME_X), clockService.load(FrameKey.FRAME_Y), 0, 0);
		} catch (IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		clockService.setHeight(this.getHeight());
		clockService.setWidth(this.getWidth());
		menuPanel.add(menuView.btn);
		menuPanel.setBackground(Color.DARK_GRAY);
		add(menuPanel, "South");
		addWindowListener(new ClockWindowAdapter());
		addComponentListener(this);
		this.setMinimumSize(new Dimension(100, 100));
	}

	public void paint(Graphics g) {
		// フレームの大きさによって文字の大きさを変える
		final Dimension size = getSize();

		// ダブルバッファリング
		final Image back = createImage(size.width, size.height);
		final Graphics buffer = back.getGraphics();
		buffer.setColor(clockService.getFontColor());
		buffer.setFont(clockService.getFontMetrics().getFont());
		buffer.drawString(clockService.getTime(),
				(int) (size.width - clockService.getFontMetrics().stringWidth(clockService.getTime())) / 2,
				(int) (size.height + clockService.getFontMetrics().getAscent()) / 2);
		g.drawImage(back, 0, 0, this);
	}

	@Override
	public void run() {
		while (true) {
			clockService.setHour(Calendar.getInstance().get(Calendar.HOUR_OF_DAY));
			clockService.setMinute(Calendar.getInstance().get(Calendar.MINUTE));
			clockService.setSecond(Calendar.getInstance().get(Calendar.SECOND));
//			clockService.save(FrameKey.FRAME_X, this.getBounds().x);
//			clockService.save(FrameKey.FRAME_Y, this.getBounds().y);
//			clockService.save(FrameKey.HEIGHT, this.getSize().height);
//			clockService.save(FrameKey.WIDTH, this.getSize().width);
			repaint();
			try {
				Thread.sleep(1000); // スリープ１秒
				resizedFlg = true;
			} catch (InterruptedException e) {
			}
		}
	}

	public static final ClockView getInstance() {
		return clockView;
	}

	@Override
	public void componentResized(ComponentEvent e) {
		Dimension size = this.getSize();
		FontMetrics fontMetrics = clockService.getFontMetrics();
		if (Objects.isNull(fontMetrics)) {
			return;
		} else if (!resizedFlg) {
			return;
		}
		while (size.width
				- fontMetrics.stringWidth(clockService.getTime()) < MIN_LIMIT_FOR_WIDTH_BETWEEN_FRAME_AND_FONT) {
			clockService.setFontSize(Integer.toString(clockService.getFontSize() - 1));
			fontMetrics = getFontMetrics(new Font(clockService.getFont(), Font.PLAIN, clockService.getFontSize()));
			clockService.setFontMetrics(fontMetrics);
		}
		while (size.width
				- fontMetrics.stringWidth(clockService.getTime()) > MAX_LIMIT_FOR_WIDTH_BETWEEN_FRAME_AND_FONT) {
			clockService.setFontSize(Integer.toString(clockService.getFontSize() + 1));
			fontMetrics = getFontMetrics(new Font(clockService.getFont(), Font.PLAIN, clockService.getFontSize()));
			clockService.setFontMetrics(fontMetrics);
		}
	}

	@Override
	public void componentMoved(ComponentEvent e) {
		clockService.setFrameX(this.getBounds().x);
		clockService.setFrameY(this.getBounds().y);
	}

	@Override
	public void componentShown(ComponentEvent e) {
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	public void componentHidden(ComponentEvent e) {
		// TODO 自動生成されたメソッド・スタブ

	}
}
