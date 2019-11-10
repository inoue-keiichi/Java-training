package dc1_2;

import java.awt.Dialog;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class PropertyDialogWindowAdapter extends WindowAdapter {
	
	public void windowClosing(WindowEvent e) {
		PropertyDialog.propertyDialog.dispose();
	}
}
