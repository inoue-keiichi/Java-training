package dc2_3.interfaces;

import java.awt.Graphics;

import javax.swing.Icon;

public interface ItemIcon {
	Icon icon = null;

	public void paintComponent(Graphics g);

	public Icon getIcon();
}
