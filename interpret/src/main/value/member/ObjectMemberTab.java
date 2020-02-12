package main.value.member;

import javax.swing.JTabbedPane;

public class ObjectMemberTab extends JTabbedPane {
	private static final ObjectMemberTab objectMemberTab = new ObjectMemberTab();

	private ObjectMemberTab() {
		this.addTab("constructor", ConstructorPanel.getInstance());
		this.addTab("field", FieldPanel.getInstance());
		this.addTab("method", MethodPanel.getInstance());
	}

	public static ObjectMemberTab getInstance() {
		return objectMemberTab;
	}
}
