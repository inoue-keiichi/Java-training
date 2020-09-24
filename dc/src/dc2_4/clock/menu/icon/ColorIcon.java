package dc2_4.clock.menu.icon;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Objects;

import javax.swing.Icon;
import javax.swing.JPanel;

import dc2_4.interfaces.ItemIcon;

public class ColorIcon extends JPanel implements ItemIcon {
	private final String name;
	private final Icon icon;

	public ColorIcon(String name, Color color) {
		this.name = name;
		this.icon = new FillIcon(color, 10, 10);
	}

	public void paintComponent(Graphics g) {
		icon.paintIcon(this, g, 0, 0);
	}

	public String getName() {
		return name;
	}

	public Icon getIcon() {
		return icon;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof ColorIcon)) {
			return false;
		}
		ColorIcon colorIcon = (ColorIcon) obj;
		if (!Objects.equals(this.name, colorIcon.getName())) {
			return false;
		}
		return true;
	}
}