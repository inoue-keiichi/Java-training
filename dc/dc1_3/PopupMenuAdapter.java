package dc1_3;

import java.awt.PopupMenu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Arrays;

public class PopupMenuAdapter implements ActionListener {
	private final ClockService clockService = ClockService.getInstance();
	private final PopupMenu popupMenu;
	private final String popupName;
	private String command;

	public PopupMenuAdapter(PopupMenu popupMenu) {
		this.popupMenu = popupMenu;
		this.popupName = popupMenu.getLabel();
		this.popupMenu.addActionListener(this);
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
		System.out.println("popupName: " + popupName);
		System.out.println("command: " + command);
		clockService.convertCommand(popupName, command);

	}
}
