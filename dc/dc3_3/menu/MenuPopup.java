package dc3_3.menu;

import static dc3_3.time.TimeService.MenuKey.*;

import javafx.event.ActionEvent;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;

public class MenuPopup {
	private final ContextMenu contextMenu;
	private final Menu fontFamilyMenu;
	private final Menu fontSizeMenu;
	private final Menu fontColorMenu;
	private final Menu backgroundColorMenu;
	private final MenuItem exitMenuItem;

	public MenuPopup() {

		contextMenu = new ContextMenu();
		fontFamilyMenu = MenuFactory.create(FONT_FAMILY, MenuService.FONT_FAMILY_NAMES);
		fontSizeMenu = MenuFactory.create(FONT_SIZE, MenuService.FONT_SIZES);
		fontColorMenu = MenuFactory.create(FONT_COLOR, MenuService.COLORS);
		backgroundColorMenu = MenuFactory.create(BACKGROUND_COLOR, MenuService.COLORS);
		exitMenuItem = new MenuItem("Exit");
		contextMenu.getItems().addAll(fontFamilyMenu, fontSizeMenu, fontColorMenu, backgroundColorMenu, exitMenuItem);

		exitMenuItem.addEventHandler(ActionEvent.ACTION, event -> {
			System.exit(0);
		});
	}

	public ContextMenu getContextMenu() {
		return contextMenu;
	}
}
