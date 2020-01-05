package dc1_4;

import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.event.ActionEvent;

public class MenuOkButton extends AbstractOkButton {
	private final MenuService menuService = MenuService.getInstance();
	private final ClockService clockService = ClockService.getInstance();
	private MenuDialogView menuDialogView = MenuDialogView.getInstance();
	private ClockView clockView;

	@Override
	public void actionPerformed(ActionEvent e) {
		menuDialogView.getInstance().dispose();

		clockService.setFont(menuService.getFontChoice().getSelectedItem());
		clockService.setFontSize(menuService.getFontSizeChoice().getSelectedItem());
		clockService.setFontColor(menuService.getFontColorChoice().getSelectedItem());
		clockService.setBackgroundColor(menuService.getBackgroundColorChoice().getSelectedItem());

		clockView = ClockView.getInstance();
		clockView.setBackground(clockService.getBackgroundColor());
		FontMetrics fontMetrics = clockView
				.getFontMetrics(new Font(clockService.getFont(), Font.PLAIN, clockService.getFontSize()));
		clockService.setFontMetrics(fontMetrics);
		clockService.setWidth(fontMetrics.stringWidth(clockService.getTime()));
		clockService.setHeight(fontMetrics.getHeight());
		clockView.setBounds(clockView.getBounds().x, clockView.getBounds().y, clockService.getWidth(),
				clockService.getHeight());
	}
}
