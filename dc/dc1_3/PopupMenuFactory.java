package dc1_3;

import java.awt.MenuItem;
import java.awt.PopupMenu;

public class PopupMenuFactory {
	public static PopupMenuAdapter create(String name, String[] menuItemNames) {
		final PopupMenu popupMenu = new PopupMenu(name);
		final PopupMenuAdapter popupMenuAdapter = new PopupMenuAdapter(popupMenu);
		for (String menuItemName : menuItemNames) {
			popupMenu.add(new MenuItem(menuItemName));
		}
		return popupMenuAdapter;
	}
}
