package dc2_3.clock.popup;

import java.awt.Color;
import java.util.Map;

import javax.swing.Icon;
import javax.swing.JRadioButtonMenuItem;

import dc2_3.di.DIGenerator;
import dc2_3.di.DIService;
import dc2_3.view.MenuView;

public class MenuFactory {
	public Icon icon;

	public static MenuView create(DIGenerator generator, DIService service, String name, String[] menuItemNames) {
		final MenuView menuView = new MenuView(generator, service);
		menuView.view.setText(name);
		final MenuTemplate menuTemplate = new MenuTemplate(menuView);
		for (String menuItemName : menuItemNames) {
			final JRadioButtonMenuItem item = new JRadioButtonMenuItem(menuItemName);
			menuView.itemList.add(item);
			item.addActionListener(menuTemplate);
			menuView.view.add(item);
			menuView.group.add(item);
		}
		return menuTemplate.menuView;
	}

	public static MenuView create(DIGenerator generator, DIService service, String name, Map<String, Color> map) {
		final MenuView menuView = new MenuView(generator, service);
		menuView.view.setText(name);
		final MenuTemplate menuTemplate = new MenuTemplate(menuView);
		for (String menuItemName : map.keySet()) {
			ColorIcon colorIcon = new ColorIcon(map.get(menuItemName));
			final JRadioButtonMenuItem item = new JRadioButtonMenuItem(menuItemName);
			item.setBackground(new Color(100, 200, 100));
			item.setIcon(colorIcon.getIcon());
			menuView.itemList.add(item);
			item.addActionListener(menuTemplate);
			menuView.view.add(item);
			menuView.group.add(item);
		}
		return menuTemplate.menuView;
	}
}
