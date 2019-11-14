package dc1_2;

import java.awt.Button;
import java.awt.Color;
import java.awt.Dialog;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MenuView implements ActionListener {
	private static final MenuView menuView = new MenuView(ClockFrame.getInstance());
	final MenuController menuController = MenuController.getInstance();
	Button btn;
	Dialog dialog;
	Frame frame;

	private MenuView(final Frame frame) {
		this.btn = new Button("menu");
		this.btn.setBounds(50, 80, 100, 30);
		this.btn.setBackground(Color.getColor("blue"));
		this.btn.addActionListener(this);
		this.frame = frame;
	}

	public void actionPerformed(ActionEvent e) {
		menuController.setVisible(true);
	}

	public void windowClosing(WindowEvent e) {
		System.exit(0);
	}

	public static MenuView getInstance() {
		return menuView;
	}

}
