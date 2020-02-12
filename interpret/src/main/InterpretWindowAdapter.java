package main;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

class InterpretWindowAdapter extends WindowAdapter {
	public void windowClosing(WindowEvent e) {
		System.exit(0);
	}
}
