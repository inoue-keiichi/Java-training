package main.array.member;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

import main.AbstractButton;

public class ConstructorCancelListener implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		ConstructorDialog.getInstance().dispose();
	}
}