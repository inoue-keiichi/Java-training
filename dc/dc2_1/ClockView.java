package dc2_1;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JFrame;

class ClockView extends JFrame {
	private final TimePanel timePanel;

	public ClockView() {
		this.timePanel = new TimePanel();
		this.add(this.timePanel);
		this.setSize(500, 200);
		this.addWindowListener(new ClockWindowAdapter());
		this.setBackground(Color.BLACK);
		this.setVisible(true);
		this.setMinimumSize(new Dimension(100, 100));
	}
}
