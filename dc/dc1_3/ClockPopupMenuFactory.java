package dc1_3;

import java.awt.MenuItem;
import java.awt.PopupMenu;

public class ClockPopupMenuFactory {
	public static PopupMenuTemplate create(String name, String[] menuItemNames) {
		final PopupMenu popupMenu = new PopupMenu(name);
		final PopupMenuTemplate popupMenuAdapter = new PopupMenuTemplate(popupMenu);
		for (String menuItemName : menuItemNames) {
			popupMenu.add(new MenuItem(menuItemName));
		}
		return popupMenuAdapter;
	}
}
