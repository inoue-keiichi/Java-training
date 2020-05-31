package dc2_3.clock.popup;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Objects;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

public class ScrollMouseLisner extends MouseAdapter {
	public static final int POPUP_ITEM_DISPLAY_NUMBER = 30;
	private boolean flag = false;
	private JMenu menu;
	private Thread thread = new Thread();

	public ScrollMouseLisner(final JMenu menu) {
		this.menu = menu;
	}

	int i = 1;

	public void mouseMoved(MouseEvent e) {
		String command = ((JMenuItem) e.getComponent()).getText();
		if (Objects.equals(command, "▼")) {
			scrollDown();
		} else if (Objects.equals(command, "▲")) {
			scrollUp();
		}
		try {
			Thread.sleep(10);
		} catch (InterruptedException e1) {
			// TODO 自動生成された catch ブロック
			e1.printStackTrace();
		}
	}

	private void scrollDown() {
		if (i + POPUP_ITEM_DISPLAY_NUMBER > menu.getItemCount() - 2) {
			return;
		}
		menu.getItem(i).setVisible(false);
		menu.getItem(i + 30).setVisible(true);
		i++;
	}

	private void scrollUp() {
		if (i < 2) {
			return;
		}
		i--;
		menu.getItem(i).setVisible(true);
		menu.getItem(i + POPUP_ITEM_DISPLAY_NUMBER).setVisible(false);
	}

	public void mouseReleased(MouseEvent e) {
		flag = false;
	}
}
