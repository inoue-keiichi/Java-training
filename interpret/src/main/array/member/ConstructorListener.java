package main.array.member;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

import main.AbstractButton;

public class ConstructorButton extends AbstractButton implements ActionListener {
	private static final ConstructorDialog constructorDialog = ConstructorDialog.getInstance();

	public ConstructorButton() {
		super("     ");
		this.addActionListener(this);
	}

	public void windowClosing(WindowEvent e) {
		System.exit(0);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// constructorDialog.setLocation(x, y);
		constructorDialog.setVisible(true);
	}

}
