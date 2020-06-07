package dc2_3.clock.popup;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.Icon;
import javax.swing.JMenuItem;
import javax.swing.JRadioButtonMenuItem;

import dc2_3.clock.popup.icon.ColorIcon;
import dc2_3.clock.popup.icon.FontIcon;
import dc2_3.clock.popup.listener.ConformCommandMouseListener;
import dc2_3.clock.popup.listener.ScrollMouseListener;
import dc2_3.di.DIGenerator;
import dc2_3.di.DIService;
import dc2_3.interfaces.ItemIcon;
import dc2_3.view.MenuView;

public class MenuFactory {
	public static final int MENU_ITEM_DISPLAY_NUMBER = 30;

	public Icon icon;

	public static <T> MenuView create(DIGenerator generator, DIService service, String name, String[] menuItemNames) {
		final MenuView menuView = new MenuView(generator, service);
		menuView.view.setText(name);
		final MenuTemplate menuTemplate = new MenuTemplate(menuView);

		if (menuItemNames.length <= MENU_ITEM_DISPLAY_NUMBER) {
			for (String itemName : menuItemNames) {
				addItem(menuView, itemName, menuTemplate);
			}
			return menuTemplate.menuView;
		}

		final MouseAdapter mouse = new ScrollMouseListener(menuView.view);
		final JMenuItem upButton = new JMenuItem("▲");
		upButton.setBackground(new Color(255, 245, 240));
		upButton.setBounds(100, 100, 100, 100);
		upButton.setEnabled(false);
		upButton.addMouseMotionListener(mouse);
		upButton.addMouseListener(mouse);
		menuView.view.add(upButton);

		for (String itemName : menuItemNames) {
			addItem(menuView, itemName, menuTemplate);
		}

		for (int i = MENU_ITEM_DISPLAY_NUMBER + 1; i < menuView.view.getItemCount(); i++) {
			menuView.view.getItem(i).setVisible(false);
		}

		final JMenuItem downButton = new JMenuItem("▼");
		downButton.setBackground(new Color(255, 245, 240));
		downButton.setEnabled(false);
		downButton.addMouseMotionListener(mouse);
		downButton.addMouseListener(mouse);
		menuView.view.add(downButton);

		return menuTemplate.menuView;
	}

	//	private <T> void addItems(final MenuView menuView, final Object items, final MenuTemplate menuTemplate) {
	//		if (items instanceof String[]) {
	//			for (String itemName : (String[]) items) {
	//				addItem(menuView, itemName, menuTemplate);
	//			}
	//		} else if (items instanceof Map) {
	//			Map<String, T> map = (Map<String, T>) items;
	//			for (Entry<String, T> entry : map.entrySet()) {
	//				addItemWithIcon(menuView, entry, menuTemplate);
	//			}
	//		}
	//	}

	private static void addItem(final MenuView menuView, final String menuItemName,
			final MenuTemplate menuTemplate) {
		final JRadioButtonMenuItem item = new JRadioButtonMenuItem(menuItemName);
		menuView.itemList.add(item);
		item.addActionListener(menuTemplate);
		item.addMouseListener(new ConformCommandMouseListener(menuView));
		menuView.view.add(item);
		menuView.group.add(item);
	}

	public static <T> MenuView create(DIGenerator generator, DIService service, String name, Map<String, T> map) {
		final MenuView menuView = new MenuView(generator, service);
		menuView.view.setText(name);
		final MenuTemplate menuTemplate = new MenuTemplate(menuView);

		if (map.keySet().size() <= MENU_ITEM_DISPLAY_NUMBER) {
			for (Entry<String, T> entry : map.entrySet()) {
				addItemWithIcon(menuView, entry, menuTemplate);
			}
			return menuTemplate.menuView;
		}

		final MouseAdapter mouse = new ScrollMouseListener(menuView.view);
		final JMenuItem upButton = new JMenuItem("▲");
		upButton.setBackground(new Color(255, 245, 240));
		upButton.setBounds(100, 100, 100, 100);
		upButton.setEnabled(false);
		upButton.addMouseMotionListener(mouse);
		upButton.addMouseListener(mouse);
		menuView.view.add(upButton);

		for (Entry<String, T> entry : map.entrySet()) {
			addItemWithIcon(menuView, entry, menuTemplate);
		}

		for (int i = MENU_ITEM_DISPLAY_NUMBER + 1; i < menuView.view.getItemCount(); i++) {
			menuView.view.getItem(i).setVisible(false);
		}

		final JMenuItem downButton = new JMenuItem("▼");
		downButton.setBackground(new Color(255, 245, 240));
		downButton.setEnabled(false);
		downButton.addMouseMotionListener(mouse);
		downButton.addMouseListener(mouse);
		menuView.view.add(downButton);

		return menuTemplate.menuView;
	}

	private static <T> void addItemWithIcon(MenuView menuView, Entry<String, T> entry, MenuTemplate menuTemplate) {
		JRadioButtonMenuItem item = null;
		ItemIcon icon = null;
		if (entry.getValue() instanceof Color) {
			icon = new ColorIcon((Color) entry.getValue());
			item = new JRadioButtonMenuItem(entry.getKey());
			item.setIcon(icon.getIcon());
		} else if (entry.getValue() instanceof Font) {
			icon = new FontIcon((Font) entry.getValue());
			item = new JRadioButtonMenuItem(entry.getKey(), icon.getIcon());
		}
		menuView.itemList.add(item);
		item.addActionListener(menuTemplate);
		menuView.view.add(item);
		menuView.group.add(item);
	}
}
