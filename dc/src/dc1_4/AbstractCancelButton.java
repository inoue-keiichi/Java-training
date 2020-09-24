package dc1_4;

import java.awt.event.ActionEvent;

public abstract class AbstractCancelButton extends AbstractButton {

	protected AbstractCancelButton() {
		super("Cancel");
		setBounds(50, 80, 100, 30);
		addActionListener(this);
	}

	@Override
	public abstract void actionPerformed(ActionEvent e);

}
