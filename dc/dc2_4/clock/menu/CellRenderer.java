package dc2_4.clock.menu;

import java.awt.Component;
import java.util.Objects;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

import dc2_4.interfaces.ItemIcon;

public class CellRenderer extends JLabel implements ListCellRenderer<ItemIcon> {
	@Override
	public Component getListCellRendererComponent(JList<? extends ItemIcon> list, ItemIcon value, int index,
			boolean isSelected, boolean cellHasFocus) {
		if (Objects.nonNull(value.getName())) {
			this.setText(value.getName());
		}
		this.setIcon(value.getIcon());
		return this;
	}
}
