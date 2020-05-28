package dc2_3.clock.time;

import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;

import javax.swing.JPanel;

import dc2_3.abstracts.PrintGenerator;
import dc2_3.clock.ClockWindowPrintGenerator;
import dc2_3.clock.ClockWindowService;
import dc2_3.di.DIGenerator;
import dc2_3.di.DIService;
import dc2_3.interfaces.Observer;

public class TimePanel extends JPanel implements Observer, Runnable {
	private final TimeService timeService;
	private final ClockWindowPrintGenerator clockWindowPrintGenerator;
	private final Thread thread;
	private boolean isShown = false;

	public TimePanel(final DIGenerator generator, final DIService service) {
		this.clockWindowPrintGenerator = generator.clockFramePrintGenerator;
		this.timeService = service.timeService;
		this.thread = new Thread(this);
		this.setBackground(this.timeService.getBackgroundColor());
		this.thread.start();
		final FontMetrics fontMetrics = getFontMetrics(
				new Font(timeService.getFont(), Font.PLAIN, timeService.getFontSize()));
		timeService.setFontMetrics(fontMetrics);

		//DI
		generator.timePanelPrintGenerator.addObserver(this);
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
		//		g.drawString(timeService.getTime(), (size.width - fontMetrics.stringWidth(this.timeService.getTime())) / 2,
		//				(int) ((size.height - fontMetrics.getHeight()) / 2 + fontMetrics.getAscent()));
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
	public void update(PrintGenerator printGenerator) {
		this.show();
		this.setBackground(this.timeService.getBackgroundColor());
		final FontMetrics fontMetrics = this
				.getFontMetrics(new Font(timeService.getFont(), Font.PLAIN, timeService.getFontSize()));
		this.timeService.setFontMetrics(fontMetrics);
		this.clockWindowPrintGenerator.execute();
		this.timeService.setFontMetricsOffsetX(ClockWindowService.SPACE_X / 2);
		this.timeService
				.setFontMetricsOffsetY(ClockWindowService.SPACE_Y / 2 + fontMetrics.getAscent());
	}
}
