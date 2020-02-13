package main.array.member;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

import main.AbstractButton;

public class ConstructorOkButton extends AbstractButton implements ActionListener {
	// private static final ConstructorDialog constructorDialog =
	// ConstructorDialog.getInstance();

	public ConstructorOkButton() {
		super("OK");
		this.addActionListener(this);
	}

	public void windowClosing(WindowEvent e) {
		System.exit(0);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		ConstructorDialog.getInstance().dispose();
	}
}
