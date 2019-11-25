package dc1_3;

import dc1_3.ClockService;
import dc1_3.ClockView;

public class Main {
	public static void main(String[] args) {
		ClockView.getInstance().setBackground(ClockService.getInstance().getBackgroundColor());
		ClockView.getInstance().setVisible(true);
		ClockView.thread.start();
	}
}
