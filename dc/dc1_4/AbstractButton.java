package dc1_4;

import java.awt.Button;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public abstract class AbstractButton extends Button implements ActionListener {

	protected AbstractButton(String str) {
		super(str);
	}

	public abstract void actionPerformed(ActionEvent e);
}
