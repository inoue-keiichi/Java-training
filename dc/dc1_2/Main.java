package dc1_2;

import java.awt.Button;
import java.awt.Color;

public class Main {
	public static void main(String[] args) {
		ClockLayout.layout.setVisible(true);
		ClockFrame.thread.start();
	}
}
