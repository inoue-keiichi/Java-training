package dc2_3.clock.popup.listener;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import dc2_3.clock.popup.ClockPopup;

public class ClickPopupListener extends MouseAdapter {
	private ClockPopup popup;

	public ClickPopupListener(final ClockPopup popup) {
		this.popup = popup;
	}

	@Override
	public void mousePressed(MouseEvent e) {
		this.showPopup(e);
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		this.showPopup(e);
	}

	private void showPopup(MouseEvent e) {
		if (e.isPopupTrigger()) {
			popup.view.show(e.getComponent(), e.getX(), e.getY());
		}
	}
}
