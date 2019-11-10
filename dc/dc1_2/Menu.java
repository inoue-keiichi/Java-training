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

public class Menu implements ActionListener {
	Button btn;
	Dialog dialog;
	Frame frame;
	
	static final Menu menu = new Menu(ClockLayout.layout);

	Menu(final Frame frame) {
		this.btn = new Button("menu");
		this.btn.setBounds(50, 80, 100, 30);
		this.btn.setBackground(Color.getColor("blue"));
		this.btn.addActionListener(this);
		this.frame = frame;
	}

	public void actionPerformed(ActionEvent e) {
		PropertyDialog.propertyDialog.setVisible(true);
	}

	public void windowClosing(WindowEvent e) {
		System.exit(0);
	}

}
