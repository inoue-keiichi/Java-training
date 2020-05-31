package dc2_3.clock.popup;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.Icon;
import javax.swing.JMenuItem;
import javax.swing.JRadioButtonMenuItem;

import dc2_3.di.DIGenerator;
import dc2_3.di.DIService;
import dc2_3.interfaces.ItemIcon;
import dc2_3.view.MenuView;

public class MenuFactory {
	public Icon icon;

	public static MenuView create(DIGenerator generator, DIService service, String name, String[] menuItemNames) {
		final MenuView menuView = new MenuView(generator, service);
		menuView.view.setText(name);
		final MenuTemplate menuTemplate = new MenuTemplate(menuView);
		final JMenuItem upButton = new JMenuItem("▲");
		final JMenuItem downButton = new JMenuItem("▼");
		final MouseAdapter mouse = new ScrollMouseLisner(menuView.view);
		upButton.setEnabled(false);
		upButton.addMouseMotionListener(mouse);
		upButton.addMouseListener(mouse);
		downButton.setEnabled(false);
		downButton.addMouseMotionListener(mouse);
		downButton.addMouseListener(mouse);
		menuView.view.add(upButton);
		for (int i = 0; i < menuItemNames.length; i++) {
			final JRadioButtonMenuItem item = new JRadioButtonMenuItem(menuItemNames[i]);
			menuView.itemList.add(item);
			item.addActionListener(menuTemplate);
			item.addMouseWheelListener(mouse);
			menuView.view.add(item);
			menuView.group.add(item);
			if (i > 30 - 1) {
				item.setVisible(false);
			}
		}
		menuView.view.add(downButton);
		return menuTemplate.menuView;
	}

	public static <T> MenuView create(DIGenerator generator, DIService service, String name, Map<String, T> map) {
		final MenuView menuView = new MenuView(generator, service);
		menuView.view.setText(name);
		final MenuTemplate menuTemplate = new MenuTemplate(menuView);
		//for (String menuItemName : map.keySet()) {
		//ColorIcon colorIcon = new ColorIcon(map.get(menuItemName));

		//			ItemIcon icon = IconFactory.create(map.get(menuItemName));
		//			final JRadioButtonMenuItem item = new JRadioButtonMenuItem(menuItemName);
		//			item.setBackground(new Color(100, 200, 100));
		//			item.setIcon(icon.getIcon());
		//			menuView.itemList.add(item);
		//			item.addActionListener(menuTemplate);
		//			menuView.view.add(item);
		//			menuView.group.add(item);
		//}

		JMenuItem upButton = null;
		JMenuItem downButton = null;
		final MouseAdapter mouse = new ScrollMouseLisner(menuView.view);

		if (map.keySet().size() > 30) {
			upButton = new JMenuItem("▲");
			upButton.setEnabled(false);
			upButton.addMouseMotionListener(mouse);
			upButton.addMouseListener(mouse);
			menuView.view.add(upButton);
		}

		for (Entry<String, T> entry : map.entrySet()) {
			addItemWithIcon(menuView, entry, menuTemplate);
		}

		for (int i = 1; i < menuView.view.getItemCount(); i++) {
			if (i > 30 - 1) {
				menuView.view.getItem(i).setVisible(false);
			}
		}

		if (map.keySet().size() > 30) {
			downButton = new JMenuItem("▼");
			downButton.setEnabled(false);
			downButton.addMouseMotionListener(mouse);
			downButton.addMouseListener(mouse);
			menuView.view.add(downButton);
		}

		return menuTemplate.menuView;
	}

	private static <T> void addItemWithIcon(MenuView menuView, Entry<String, T> entry, MenuTemplate menuTemplate) {
		JRadioButtonMenuItem item = null;
		ItemIcon icon = null;
		if (entry.getValue() instanceof Color) {
			icon = new ColorIcon((Color) entry.getValue());
			item = new JRadioButtonMenuItem(entry.getKey());
			item.setBackground(new Color(100, 200, 100));
			item.setIcon(icon.getIcon());
		} else if (entry.getValue() instanceof Font) {
			icon = new FontIcon((Font) entry.getValue());
			item = new JRadioButtonMenuItem(entry.getKey(), icon.getIcon());
			//item = new JRadioButtonMenuItem(icon.getIcon());
		}
		menuView.itemList.add(item);
		item.addActionListener(menuTemplate);
		menuView.view.add(item);
		menuView.group.add(item);
	}
}
