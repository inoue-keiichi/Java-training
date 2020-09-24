package dc2_3.clock.listener;

import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Objects;

import javax.swing.JComponent;
import javax.swing.JWindow;
import javax.swing.SwingUtilities;

public class DragWindowListener extends MouseAdapter {
	private final Point startPt = new Point();
	private JWindow window;

	@Override
	public void mousePressed(MouseEvent e) {
		if (Objects.isNull(window)) {
			Object obj = e.getSource();
			if (obj instanceof JWindow) {
				window = (JWindow) obj;
			} else if (obj instanceof JComponent) {
				window = (JWindow) SwingUtilities.windowForComponent(e.getComponent());
			}
		}
		startPt.setLocation(e.getPoint());
	}

	@Override
	public void mouseDragged(MouseEvent e) {

		Point eventLocationOnScreen = e.getLocationOnScreen();
		window.setLocation(eventLocationOnScreen.x - startPt.x,
				eventLocationOnScreen.y - startPt.y);
	}

}
