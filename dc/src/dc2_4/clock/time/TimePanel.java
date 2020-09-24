package dc2_4.clock.time;

import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

import javax.swing.JPanel;

import dc2_4.abstracts.PrintGenerator;
import dc2_4.clock.ClockFrameService;
import dc2_4.di.DIGenerator;
import dc2_4.di.DIService;
import dc2_4.interfaces.Observer;

public class TimePanel extends JPanel implements Observer, Runnable, ComponentListener {
	private static final int MIN_LIMIT_FOR_WIDTH_BETWEEN_FRAME_AND_FONT = 10;
	private static final int MAX_LIMIT_FOR_WIDTH_BETWEEN_FRAME_AND_FONT = 20;

	private final TimeService timeService;
	private final Thread thread;
	private boolean isShown = false;
	private boolean resizedFlg;

	private int i = 0;
	private int j = 0;

	private final PrintGenerator clockFramePrintGenerator;
	private final PrintGenerator timePanelPrintGenerator;
	private final ClockFrameService clockFrameService;

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
		generator.timePanelPrintGenerator.addObserver(this);
		this.clockFramePrintGenerator = generator.clockFramePrintGenerator;
		this.timePanelPrintGenerator = generator.timePanelPrintGenerator;
		this.clockFrameService = service.clockFrameService;
	}

	public void paintComponent(Graphics g) {
		if (!isShown) {
			return;
		}
		// フレームの大きさによって文字の大きさを変える
		FontMetrics fontMetrics = getFontMetrics(
				new Font(timeService.getFont(), Font.PLAIN, timeService.getFontSize()));
		g.setColor(timeService.getFontColor());
		g.setFont(fontMetrics.getFont());
		g.drawString(timeService.getTime(), this.timeService.getFontMetricsOffsetX(),
				this.timeService.getFontMetricsOffsetY());
	}

	@Override
	public void run() {
		while (true) {
			timeService.updateTime();
			repaint();
			try {
				Thread.sleep(1000); // スリープ１秒
			} catch (InterruptedException e) {
			}
		}
	}

	public void show() {
		isShown = true;
	}

	@Override
	public void componentResized(ComponentEvent e) {
		//		Dimension size = this.getSize();
		//		//System.out.println(size.getWidth() + " : " + size.getHeight());
		//		FontMetrics fontMetrics = timeService.getFontMetrics();
		//		if (Objects.isNull(fontMetrics)) {
		//			return;
		//		} else if (!this.resizedFlg) {
		//			return;
		//		}
		//
		//		int i = 0;
		//
		//		while (size.width != this.clockFrameService.getSpaceX() + fontMetrics.stringWidth(timeService.getTime())
		//				|| size.height != this.clockFrameService.getSpaceY()
		//						+ fontMetrics.stringWidth(timeService.getTime())) {
		//			if (size.width < this.clockFrameService.getSpaceX() + fontMetrics.stringWidth(timeService.getTime())
		//					|| size.height < this.clockFrameService.getSpaceY() + fontMetrics.getHeight()) {
		//				timeService.setFontSize(Integer.toString(timeService.getFontSize() - 1));
		//				fontMetrics = getFontMetrics(new Font(timeService.getFont(), Font.PLAIN, timeService.getFontSize()));
		//				timeService.setFontMetrics(fontMetrics);
		//			} else if (size.width > this.clockFrameService.getSpaceX() + fontMetrics.stringWidth(timeService.getTime())
		//					|| size.height > this.clockFrameService.getSpaceY() + fontMetrics.getHeight()) {
		//				timeService.setFontSize(Integer.toString(timeService.getFontSize() + 1));
		//				fontMetrics = getFontMetrics(new Font(timeService.getFont(), Font.PLAIN, timeService.getFontSize()));
		//				timeService.setFontMetrics(fontMetrics);
		//			}
		//
		//			this.clockFrameService.setSpaceX(timeService.getFontMetrics().stringWidth(timeService.getTime()) / 4);
		//			this.clockFrameService.setSpaceY(timeService.getFontMetrics().getHeight() / 4);
		//			this.timeService.setFontMetricsOffsetX(this.clockFrameService.getSpaceX() / 2);
		//			this.timeService
		//					.setFontMetricsOffsetY(this.clockFrameService.getSpaceY() / 2 + fontMetrics.getAscent());
		//			size = this.getSize();
		//
		//			// 文字サイズの増減を交互に繰り返して無限ループする場合がある
		//			if (i > 30) {
		//				break;
		//			}
		//			i++;
		//		}
		//
		//		//		int i = 0;
		//		//
		//		//		while (size.width > (int) fontMetrics.stringWidth(timeService.getTime()) / 4 * 5 * 1.01
		//		//				|| size.width < (int) fontMetrics.stringWidth(timeService.getTime()) / 4 * 5 * 0.99) {
		//		//			if (size.width < (int) fontMetrics.stringWidth(timeService.getTime()) / 4 * 5) {
		//		//				timeService.setFontSize(Integer.toString(timeService.getFontSize() - 1));
		//		//				fontMetrics = getFontMetrics(new Font(timeService.getFont(), Font.PLAIN, timeService.getFontSize()));
		//		//				timeService.setFontMetrics(fontMetrics);
		//		//			} else if (size.width > (int) fontMetrics.stringWidth(timeService.getTime()) / 4 * 5) {
		//		//				timeService.setFontSize(Integer.toString(timeService.getFontSize() + 1));
		//		//				fontMetrics = getFontMetrics(new Font(timeService.getFont(), Font.PLAIN, timeService.getFontSize()));
		//		//				timeService.setFontMetrics(fontMetrics);
		//		//			}
		//		//
		//		//			this.clockFrameService.setSpaceX(timeService.getFontMetrics().stringWidth(timeService.getTime()) / 4);
		//		//			this.clockFrameService.setSpaceY(timeService.getFontMetrics().getHeight() / 4);
		//		//			this.timeService.setFontMetricsOffsetX(this.clockFrameService.getSpaceX() / 2);
		//		//			this.timeService
		//		//					.setFontMetricsOffsetY(this.clockFrameService.getSpaceY() / 2 + fontMetrics.getAscent());
		//		//
		//		//			if (i > 30) {
		//		//				break;
		//		//			}
		//		//			i++;
		//		//		}
		//
		//		//		while (size.height > (int) fontMetrics.getHeight() / 4 * 5 * 1.1
		//		//				|| size.height < (int) fontMetrics.getHeight() / 4 * 5 * 0.9) {
		//		//			if (size.height < (int) fontMetrics.getHeight() / 4 * 5) {
		//		//				timeService.setFontSize(Integer.toString(timeService.getFontSize() - 1));
		//		//				fontMetrics = getFontMetrics(new Font(timeService.getFont(), Font.PLAIN, timeService.getFontSize()));
		//		//				timeService.setFontMetrics(fontMetrics);
		//		//			} else if (size.height > (int) fontMetrics.getHeight() / 4 * 5) {
		//		//				timeService.setFontSize(Integer.toString(timeService.getFontSize() + 1));
		//		//				fontMetrics = getFontMetrics(new Font(timeService.getFont(), Font.PLAIN, timeService.getFontSize()));
		//		//				timeService.setFontMetrics(fontMetrics);
		//		//			}
		//		//			this.clockFrameService.setSpaceX(timeService.getFontMetrics().stringWidth(timeService.getTime()) / 4);
		//		//			this.clockFrameService.setSpaceY(timeService.getFontMetrics().getHeight() / 4);
		//		//			this.timeService.setFontMetricsOffsetX(this.clockFrameService.getSpaceX() / 2);
		//		//			this.timeService
		//		//					.setFontMetricsOffsetY(this.clockFrameService.getSpaceY() / 2 + fontMetrics.getAscent());
		//		//		}
		//
		//		//this.clockFramePrintGenerator.setChanged(false);
		//		//this.timePanelPrintGenerator.execute();
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
		// フレームのサイズを変更すると、componentResize()が動いてしまう
		this.resizedFlg = false;
		this.show();
		this.setBackground(this.timeService.getBackgroundColor());
		final FontMetrics fontMetrics = this
				.getFontMetrics(new Font(timeService.getFont(), Font.PLAIN, timeService.getFontSize()));
		this.timeService.setFontMetrics(fontMetrics);
		this.clockFramePrintGenerator.execute();
		//this.clockFramePrintGenerator.setChanged(false);
		this.timeService.setFontMetricsOffsetX(this.clockFrameService.getSpaceX() / 2);
		this.timeService
				.setFontMetricsOffsetY(this.clockFrameService.getSpaceY() / 2 + fontMetrics.getAscent());
		//System.out.println(ClockFrameService.SPACE_X / 2 + " : " + ClockFrameService.SPACE_Y / 2);
		//this.clockFramePrintGenerator.execute();
		//this.clockFramePrintGenerator.setChanged(false);
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		this.resizedFlg = true;
	}
}
