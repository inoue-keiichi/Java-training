package dc1_4;

import java.awt.event.ActionEvent;

public abstract class AbstractOkButton extends AbstractButton {

	protected AbstractOkButton() {
		super("OK");
		setBounds(50, 80, 100, 30);
		addActionListener(this);
	}

	public abstract void actionPerformed(ActionEvent e);
}
