package main.array.member;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

import main.AbstractButton;

public class ConstructorListener implements ActionListener {
	private static final ConstructorDialog constructorDialog = ConstructorDialog.getInstance();

	@Override
	public void actionPerformed(ActionEvent e) {
		// constructorDialog.setLocation(x, y);
		constructorDialog.setVisible(true);
	}

}
