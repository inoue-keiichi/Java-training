package dc1_2;

import java.awt.Button;
import java.awt.Color;

public class Main {
	public static void main(String[] args) {
		ClockView.getInstance().setBackground(ClockService.getInstance().getBackgroundColor());
		ClockView.getInstance().setVisible(true);
		ClockView.thread.start();
	}
}
