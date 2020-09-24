package dc2_3.clock.popup.listener;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Objects;

import javax.swing.JMenuItem;

import dc2_3.view.MenuView;

public class ConformCommandMouseListener extends MouseAdapter {
	private final MenuView menuView;
	private final String popupName;
	private Integer preFontSize = null;

	public ConformCommandMouseListener(final MenuView menuView) {
		this.menuView = menuView;
		this.popupName = menuView.view.getText();
	}

	public void mouseEntered(final MouseEvent e) {
		if (Objects.isNull(preFontSize)) {
			preFontSize = menuView.service.timeService.getFontSize();
		}

		String command = ((JMenuItem) e.getComponent()).getText();
		menuView.service.timeService.convertCommand(popupName, command);
		menuView.service.clockFrameService.convertCommand(popupName, command);
		this.menuView.generator.timePanelPrintGenerator.execute();
	}

	public void mouseExited(final MouseEvent e) {
		menuView.service.timeService.setFontSize(preFontSize);
		this.menuView.generator.timePanelPrintGenerator.execute();
		preFontSize = null;
	}
}
