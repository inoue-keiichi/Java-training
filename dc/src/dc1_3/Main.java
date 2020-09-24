package dc1_3;

import java.awt.Font;
import java.awt.FontMetrics;

import dc1_3.ClockService;
import dc1_3.ClockView;

public class Main {
	public static void main(String[] args) {
		final ClockService clockService = ClockService.getInstance();
		final ClockView clockView = ClockView.getInstance();
		final FontMetrics fontMetrics = clockView
				.getFontMetrics(new Font(clockService.getFont(), Font.PLAIN, clockService.getFontSize()));
		ClockService.getInstance().setFontMetrics(
				clockView.getFontMetrics(new Font(clockService.getFont(), Font.PLAIN, clockService.getFontSize())));
		clockView.setBackground(ClockService.getInstance().getBackgroundColor());
		clockView.setVisible(true);
		clockService.setWidth(fontMetrics.stringWidth(clockService.getTime()));
		clockService.setHeight(fontMetrics.getHeight());
		clockView.setBounds(clockView.getBounds().x, clockView.getBounds().y, clockService.getWidth(),
				clockService.getHeight());
		ClockView.thread.start();
	}
}
