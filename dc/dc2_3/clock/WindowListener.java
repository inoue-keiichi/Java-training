package dc2_3.clock;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

class WindowListener extends WindowAdapter {
	public void windowClosing(WindowEvent e) {
		System.exit(0);
	}
}
