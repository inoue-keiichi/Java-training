package dc2_3.clock.popup;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JRadioButtonMenuItem;

import dc2_3.di.DIGenerator;
import dc2_3.di.DIService;
import dc2_3.view.MenuView;
import dc2_3.view.PopupView;

public class ClockPopup extends PopupView implements ActionListener {

	private final JPopupMenu popupMenu = new JPopupMenu("Property");
	private final MenuView fontMenuView;
	private final MenuView fontSizeMenuView;
	private final MenuView fontColorMenuView;
	private final MenuView backgroundColorMenuView;
	private final JMenuItem menuFinishItem;

	public ClockPopup(DIGenerator generator, DIService service) {
		super(generator, service);
		view.setName("Property");
		fontMenuView = MenuFactory.create(generator, service, "Font", PopupMenuUtility.FONTMAP);
		fontSizeMenuView = MenuFactory.create(generator, service, "Font Size", PopupMenuUtility.FONT_SIZES);
		fontColorMenuView = MenuFactory.create(generator, service, "Font Color", PopupMenuUtility.COLORMAP);
		backgroundColorMenuView = MenuFactory.create(generator, service, "Background Color", PopupMenuUtility.COLORMAP);
		menuFinishItem = new JMenuItem("Finish");
		menuFinishItem.addActionListener(this);
		view.add(fontMenuView.view);
		view.add(fontSizeMenuView.view);
		view.add(fontColorMenuView.view);
		view.add(backgroundColorMenuView.view);
		view.add(menuFinishItem);
		for (JRadioButtonMenuItem item : fontMenuView.itemList) {
			if (Objects.equals(item.getText(), "Dialog")) {
				item.doClick();
				break;
			}
		}
		for (JRadioButtonMenuItem item : fontSizeMenuView.itemList) {
			if (Objects.equals(item.getText(), "100")) {
				item.doClick();
				break;
			}
		}
		for (JRadioButtonMenuItem item : fontColorMenuView.itemList) {
			if (Objects.equals(item.getText(), "green")) {
				item.doClick();
				break;
			}
		}
		for (JRadioButtonMenuItem item : backgroundColorMenuView.itemList) {
			if (Objects.equals(item.getText(), "black")) {
				item.doClick();
				break;
			}
		}
	}

	public JPopupMenu getPopupMenu() {
		return popupMenu;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();

		if (command.equals("Finish")) {
			System.exit(0);
			return;
		}
	}
}