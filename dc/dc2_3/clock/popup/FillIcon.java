package dc2_3.clock.popup;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;

import javax.swing.Icon;

class FillIcon implements Icon {
	int width, height;
	Color color = Color.BLACK;

	public FillIcon(Color color, int width, int height) {
		this.color = color;
		this.width = width;
		this.height = height;
	}

	public int getIconWidth() {
		return width;
	}

	public int getIconHeight() {
		return height;
	}

	public void paintIcon(Component c, Graphics g, int x, int y) {
		g.setColor(color);
		g.fillRect(x, y, width, height);
		// To draw graphics with black after drawing icon.
		g.setColor(Color.black);
	}
}