package dc1_3;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

class ClockWindowAdapter extends WindowAdapter {
	public void windowClosing(WindowEvent e) {
		System.exit(0);
	}
}