package dc2_4.clock.menu.icon;

import java.awt.Font;
import java.awt.Graphics;
import java.util.Objects;

import javax.swing.Icon;
import javax.swing.JLabel;

import dc2_4.interfaces.ItemIcon;

public class FontIcon extends JLabel implements ItemIcon {
	private final String name;
	private final Icon icon;

	public FontIcon(Font font) {
		name = font.getName();
		icon = new FillIcon(font, 0, 0);
	}

	@Override
	public void paintComponent(Graphics g) {
		icon.paintIcon(this, g, 0, 0);
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public Icon getIcon() {
		return icon;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof FontIcon)) {
			return false;
		}
		FontIcon fontIcon = (FontIcon) obj;
		if (!Objects.equals(this.name, fontIcon.getName())) {
			return false;
		}
		return true;
	}
}