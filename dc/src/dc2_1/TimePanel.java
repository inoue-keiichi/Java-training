package dc2_1;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.util.Objects;

import javax.swing.JPanel;

public class TimePanel extends JPanel implements Runnable, ComponentListener {
	private static final int MIN_LIMIT_FOR_WIDTH_BETWEEN_FRAME_AND_FONT = 10;
	private static final int MAX_LIMIT_FOR_WIDTH_BETWEEN_FRAME_AND_FONT = 20;
	private static final int MIN_LIMIT_FOR_HEIGHT_BETWEEN_FRAME_AND_FONT = 100;
	private static final int MAX_LIMIT_FOR_HEIGHT_BETWEEN_FRAME_AND_FONT = 0;

	private final TimeService timeService;
	private final Thread thread;
	private boolean resizedFlg;

	public TimePanel() {
		this.timeService = new TimeService();
		this.thread = new Thread(this);
		this.resizedFlg = false;
		this.setBackground(Color.BLACK);
		this.thread.start();
		this.addComponentListener(this);
		final FontMetrics fontMetrics = getFontMetrics(
				new Font(timeService.getFont(), Font.PLAIN, timeService.getFontSize()));
		timeService.setFontMetrics(fontMetrics);
	}

	public void paintComponent(Graphics g) {
		// フレームの大きさによって文字の大きさを変える
		final Dimension size = getSize();
		FontMetrics fontMetrics = getFontMetrics(
				new Font(timeService.getFont(), Font.PLAIN, timeService.getFontSize()));
		this.setMinimumSize(new Dimension(MAX_LIMIT_FOR_HEIGHT_BETWEEN_FRAME_AND_FONT,
				fontMetrics.getHeight() + MIN_LIMIT_FOR_HEIGHT_BETWEEN_FRAME_AND_FONT));

		// ダブルバッファリング
		final Image back = createImage(size.width, size.height);
		final Graphics buffer = back.getGraphics();
		buffer.setColor(timeService.getFontColor());
		buffer.setFont(fontMetrics.getFont());
		buffer.drawString(timeService.getTime(),
				(int) (size.width - fontMetrics.stringWidth(timeService.getTime())) / 2,
				(int) (size.height + fontMetrics.getHeight()) / 2);
		g.drawImage(back, 0, 0, this);
	}

	@Override
	public void run() {
		while (true) {
			timeService.updateTime();
			repaint();
			try {
				Thread.sleep(1000); // スリープ１秒
				this.resizedFlg = true;
			} catch (InterruptedException e) {
			}
		}
	}

	@Override
	public void componentResized(ComponentEvent e) {
		Dimension size = this.getSize();
		FontMetrics fontMetrics = timeService.getFontMetrics();
		if (Objects.isNull(fontMetrics)) {
			return;
		} else if (!resizedFlg) {
			return;
		}
		while (size.width
				- fontMetrics.stringWidth(timeService.getTime()) < MIN_LIMIT_FOR_WIDTH_BETWEEN_FRAME_AND_FONT) {
			timeService.setFontSize(Integer.toString(timeService.getFontSize() - 1));
			fontMetrics = getFontMetrics(new Font(timeService.getFont(), Font.PLAIN, timeService.getFontSize()));
			timeService.setFontMetrics(fontMetrics);
		}
		while (size.width
				- fontMetrics.stringWidth(timeService.getTime()) > MAX_LIMIT_FOR_WIDTH_BETWEEN_FRAME_AND_FONT) {
			timeService.setFontSize(Integer.toString(timeService.getFontSize() + 1));
			fontMetrics = getFontMetrics(new Font(timeService.getFont(), Font.PLAIN, timeService.getFontSize()));
			timeService.setFontMetrics(fontMetrics);
		}
	}

	@Override
	public void componentMoved(ComponentEvent e) {
		// TODO 自動生成されたメソッド・スタブ

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
