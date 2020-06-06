package dc2_3.clock.popup.listener;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

public class ScrollMouseListener extends MouseAdapter {
	public static final int POPUP_ITEM_DISPLAY_NUMBER = 30;
	private JMenu menu;
	private Thread thread;
	private int i = 1;
	private final Runnable UP = new Runnable() {
		@Override
		public void run() {
			if (i < 2) {
				return;
			}
			i--;
			if (i % 30 != 0) {
				menu.getItem(i).setVisible(true);
			}
			menu.getItem(i + POPUP_ITEM_DISPLAY_NUMBER).setVisible(false);
		}
	};
	private final Runnable DOWN = new Runnable() {
		@Override
		public void run() {
			if (i + POPUP_ITEM_DISPLAY_NUMBER > menu.getItemCount() - 2) {
				return;
			}
			menu.getItem(i).setVisible(false);
			if (i % 30 != 0) {
				menu.getItem(i + POPUP_ITEM_DISPLAY_NUMBER).setVisible(true);
			}
			i++;
		}
	};
	private final Runnable WAIT = new Runnable() {
		@Override
		public void run() {
		}
	};
	private List<Runnable> queue = new ArrayList<Runnable>() {
		{
			add(WAIT);
		}
	};
	private Runnable task = new Runnable() {
		@Override
		public void run() {
			Runnable r = null;
			while (true) {
				synchronized (queue) {
					while (queue.isEmpty() && r == WAIT) {
						try {
							queue.wait();
						} catch (InterruptedException e) {
						}
					}
					if (!queue.isEmpty()) {
						r = queue.remove(0);
					}
				}
				r.run();
				try {
					Thread.sleep(30);
				} catch (InterruptedException e) {
					// TODO 自動生成された catch ブロック
					e.printStackTrace();
				}
			}
		}
	};

	public ScrollMouseListener(final JMenu menu) {
		this.menu = menu;
		thread = new Thread(this.task);
		thread.start();
	}

	public void mouseEntered(MouseEvent e) {
		String command = ((JMenuItem) e.getComponent()).getText();
		if (Objects.equals(command, "▼")) {
			scrollDown();
		} else if (Objects.equals(command, "▲")) {
			scrollUp();
		}
	}

	public void mouseExited(MouseEvent e) {
		queue.add(WAIT);
	}

	private void scrollDown() {
		queue.add(DOWN);
		synchronized (queue) {
			queue.notifyAll();
		}
	}

	private void scrollUp() {
		queue.add(UP);
		synchronized (queue) {
			queue.notifyAll();
		}
	}
}
