package dc2_4.clock.menu.icon;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.util.Objects;

import javax.swing.Icon;

public class FillIcon implements Icon {
	int width, height;
	Color color = null;
	Font font = null;

	public FillIcon(Object obj, int width, int height) {
		if (obj instanceof Color) {
			this.color = (Color) obj;
		} else if (obj instanceof Font) {
			this.font = (Font) obj;
		}
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
		if (Objects.nonNull(color)) {
			paintColorIcon(c, g, x, y);
		} else if (Objects.nonNull(font)) {
			paintFontIcon(c, g, x, y);
		}
	}

	private void paintColorIcon(Component c, Graphics g, int x, int y) {
		g.setColor(color);
		g.fillRect(x, y, width, height);
		// To draw graphics with black after drawing icon.
		g.setColor(Color.black);
		g.drawRect(x, y, width, height);
	}

	private void paintFontIcon(Component c, Graphics g, int x, int y) {
		g.setColor(Color.black);
		g.setFont(font);
	}
}