package dc2_4.clock.menu.icon;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.Icon;
import javax.swing.JPanel;

import dc2_3.clock.popup.icon.FillIcon;
import dc2_3.interfaces.ItemIcon;

public class ColorIcon extends JPanel implements ItemIcon {
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