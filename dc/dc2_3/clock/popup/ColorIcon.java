package dc2_3.clock.popup;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.Icon;
import javax.swing.JPanel;

public class ColorIcon extends JPanel {
	private final Icon icon;

	public ColorIcon(Color color) {
		icon = new FillIcon(color, 10, 10);
	}

	public void paintComponent(Graphics g) {
		icon.paintIcon(this, g, 0, 0);
	}

	public Icon getIcon() {
		return icon;
	}
}