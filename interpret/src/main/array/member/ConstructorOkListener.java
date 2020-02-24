package main.array.member;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

import main.AbstractButton;
import main.ErrorHandler;

public class ConstructorOkListener implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		ConstructorDialog.getInstance().dispose();
		try {
			ElementButtonPrintGenerator.getInstance().execute();
		} catch (Throwable e1) {
			ErrorHandler.getInstance().execute(e1);
		}
	}
}
