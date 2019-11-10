package dc1_2;

import java.awt.Button;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Panel;

public class ClockLayout extends Frame {
	static final ClockLayout layout = new ClockLayout();
	final Panel menuPanel = new Panel();
	final Panel clockPanel = new Panel();
	final Menu menu = Menu.menu;
	static Color backgroundColor = Color.black;

	public ClockLayout() {
		setLayout(new GridLayout(2, 1));
		add(ClockFrame.clock);
		add(menuPanel);
		clockPanel.setBackground(backgroundColor);
		setBackground(backgroundColor);
		ClockFrame.clock.setVisible(true);
		menuPanel.add(menu.btn);
		setSize(500, 500);
		addWindowListener(new ClockWindowAdapter());
	}
}
