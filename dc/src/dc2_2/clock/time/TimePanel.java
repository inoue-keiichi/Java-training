package dc2_2.clock.time;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.util.Objects;

import javax.swing.JPanel;

import dc2_2.abstracts.PrintGenerator;
import dc2_2.di.DIGenerator;
import dc2_2.di.DIService;
import dc2_2.interfaces.Observer;

public class TimePanel extends JPanel implements Observer, Runnable, ComponentListener {
	private static final int MIN_LIMIT_FOR_WIDTH_BETWEEN_FRAME_AND_FONT = 10;
	private static final int MAX_LIMIT_FOR_WIDTH_BETWEEN_FRAME_AND_FONT = 20;
	private static final int MIN_LIMIT_FOR_HEIGHT_BETWEEN_FRAME_AND_FONT = 100;
	private static final int MAX_LIMIT_FOR_HEIGHT_BETWEEN_FRAME_AND_FONT = 0;

	private final TimeService timeService;
	private final Thread thread;
	private boolean resizedFlg;
	public boolean isResize;
	private int width;

	public TimePanel(final DIGenerator generator, final DIService service) {
		this.timeService = service.timeService;
		this.thread = new Thread(this);
		this.resizedFlg = false;
		this.setBackground(this.timeService.getBackgroundColor());
		this.thread.start();
		this.addComponentListener(this);
		final FontMetrics fontMetrics = getFontMetrics(
				new Font(timeService.getFont(), Font.PLAIN, timeService.getFontSize()));
		timeService.setFontMetrics(fontMetrics);

		//DI
		generator.clockFramePrintGenerator.addObserver(this);

		isResize = true;
	}

	public void paintComponent(Graphics g) {
		// フレームの大きさによって文字の大きさを変える
		final Dimension size = getSize();
		FontMetrics fontMetrics = getFontMetrics(
				new Font(timeService.getFont(), Font.PLAIN, timeService.getFontSize()));
		//		this.setMinimumSize(new Dimension(
		//				fontMetrics.stringWidth(timeService.getTime()) + MAX_LIMIT_FOR_HEIGHT_BETWEEN_FRAME_AND_FONT,
		//				fontMetrics.getHeight() + MIN_LIMIT_FOR_HEIGHT_BETWEEN_FRAME_AND_FONT));
		g.setColor(timeService.getFontColor());
		g.setFont(fontMetrics.getFont());
		g.drawString(timeService.getTime(), MIN_LIMIT_FOR_WIDTH_BETWEEN_FRAME_AND_FONT,
				(int) ((size.height - fontMetrics.getHeight()) / 2 + fontMetrics.getAscent()));

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

	@Override
	public void update(PrintGenerator printGenerator) {
		this.setBackground(this.timeService.getBackgroundColor());

	}
}
