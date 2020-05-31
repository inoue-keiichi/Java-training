package dc2_3.clock.popup;

import java.awt.Font;
import java.awt.Graphics;

import javax.swing.Icon;
import javax.swing.JLabel;

import dc2_3.interfaces.ItemIcon;

public class FontIcon extends JLabel implements ItemIcon {
	private final Icon icon;
	private final String fontName;

	public FontIcon(Font font) {
		icon = new FillIcon(font, 0, 0);
		fontName = font.getName();
	}

	public void paintComponent(Graphics g) {
		icon.paintIcon(this, g, 0, 0);
	}

	public Icon getIcon() {
		return icon;
	}

	public String getFontName() {
		return fontName;
	}
}
