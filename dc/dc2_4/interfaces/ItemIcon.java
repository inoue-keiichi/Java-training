package dc2_4.interfaces;

import java.awt.Graphics;

import javax.swing.Icon;

public interface ItemIcon {
	String name = null;
	Icon icon = null;

	void paintComponent(Graphics g);

	String getName();

	Icon getIcon();
}