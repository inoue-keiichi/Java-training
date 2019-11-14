package dc1_2;

import java.awt.Button;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Panel;

public class ClockFrame extends Frame {
	private static final ClockFrame clockFrame = new ClockFrame();
	private final ClockView clockView = ClockView.getInstance();
	private final MenuView menuView = MenuView.getInstance();
	final Panel menuPanel = new Panel();
	final Panel clockPanel = new Panel();
	Color backgroundColor;

	public ClockFrame() {
		backgroundColor = Color.black;
		setLayout(new GridLayout(2, 1));
		add(clockView);
		add(menuPanel);
		clockView.setBackground(backgroundColor);
		clockView.setVisible(true);
		menuPanel.add(menuView.btn);
		menuPanel.setBackground(backgroundColor);
		setSize(500, 500);
		addWindowListener(new ClockWindowAdapter());
	}

	public static ClockFrame getInstance() {
		return clockFrame;
	}

	public void setBackground(Color backgroundColor) {
		this.backgroundColor = backgroundColor;
		menuPanel.setBackground(backgroundColor);
		clockView.setBackground(backgroundColor);
	}
}
