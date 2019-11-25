package dc1_3;

import java.awt.Component;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import static dc1_3.PopupMenuUtility.*;

public class PopupMenuView implements ActionListener {
	private static final PopupMenuView popupMenuView = new PopupMenuView("Property");
	
	private final PopupMenu popupMenu = new PopupMenu("Property");
	private final PopupMenuAdapter popupFontMenu;
	private final PopupMenuAdapter popupFontSizeMenu;
	private final PopupMenuAdapter popupFontColorMenu;
	private final PopupMenuAdapter popupBackgroundColorMenu;
	private final MenuItem menuFinishItem = new MenuItem("Finish");

	private PopupMenuView(String str) {
		popupFontMenu = PopupMenuFactory.create("Font", FONT_FAMILY_NAMES);
		popupMenu.add(popupFontMenu.getPopupMenu());
		popupFontSizeMenu = PopupMenuFactory.create("Font Size", FONT_SIZES);
		popupMenu.add(popupFontSizeMenu.getPopupMenu());
		popupFontColorMenu = PopupMenuFactory.create("Font Color", FONT_COLORS);
		popupMenu.add(popupFontColorMenu.getPopupMenu());
		popupBackgroundColorMenu = PopupMenuFactory.create("Background Color", BACKGROUND_COLORS);
		popupMenu.add(popupBackgroundColorMenu.getPopupMenu());
		popupMenu.add(menuFinishItem);
		popupMenu.addActionListener(this);
	}

	public PopupMenu getPopupMenu() {
		return popupMenu;
	}

	public static final PopupMenuView getInstance() {
		return popupMenuView;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();
		System.out.println(command);

		if (command.equals("Finish")) {
			System.exit(0);
			return;
		}
	}
}
