package dc1_3;

import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.PopupMenu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class PopupMenuTemplate implements ActionListener {
	private final ClockService clockService;
	private final PopupMenu popupMenu;
	private final String popupName;
	private String command;

	public PopupMenuTemplate(PopupMenu popupMenu) {
		this.popupMenu = popupMenu;
		this.popupName = popupMenu.getLabel();
		this.popupMenu.addActionListener(this);
		this.clockService = ClockService.getInstance();
	}

	public String getCommand() {
		return command;
	}

	public PopupMenu getPopupMenu() {
		return popupMenu;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		command = e.getActionCommand();
		ClockView clockView = ClockView.getInstance();
		System.out.println("popupName: " + popupName);
		System.out.println("command: " + command);
		clockService.convertCommand(popupName, command);
		if (!Objects.isNull(command)) {
			FontMetrics fontMetrics = clockView
					.getFontMetrics(new Font(clockService.getFont(), Font.PLAIN, clockService.getFontSize()));
			clockService.setWidth(fontMetrics.stringWidth(clockService.getTime()));
			clockService.setHeight(fontMetrics.getHeight());
			clockService.setFontMetrics(fontMetrics);
			clockView.setBounds(clockView.getBounds().x, clockView.getBounds().y, clockService.getWidth(),
					clockService.getHeight());
		}
		command = null;

	}
}
