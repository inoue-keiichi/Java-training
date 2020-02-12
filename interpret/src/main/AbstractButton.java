package main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

public abstract class AbstractButton extends JButton implements ActionListener {

	protected AbstractButton(String str) {
		super(str);
		setBounds(50, 80, 100, 30);
		addActionListener(this);
	}

	public abstract void actionPerformed(ActionEvent e);
}
