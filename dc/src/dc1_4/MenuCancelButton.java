package dc1_4;

import java.awt.event.ActionEvent;

public class MenuCancelButton extends AbstractCancelButton {

	@Override
	public void actionPerformed(ActionEvent e) {
		MenuDialogView.getInstance().dispose();
	}

}
