package main.array.member;

import javax.swing.JButton;
import javax.swing.JTabbedPane;

public class ArrayTypeMemberTab extends JTabbedPane {
	private static final ArrayTypeMemberTab arrayTypeMemberTab = new ArrayTypeMemberTab();

	private ArrayTypeMemberTab() {
		this.addTab("setter", ElementPanel.getInstance());
		this.addTab("field", ElementFieldPanel.getInstance());
		this.addTab("method", ElementMethodPanel.getInstance());
	}

	public static ArrayTypeMemberTab getInstance() {
		return arrayTypeMemberTab;
	}
}
