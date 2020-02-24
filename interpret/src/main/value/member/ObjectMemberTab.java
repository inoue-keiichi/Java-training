package main.value.member;

import javax.swing.JTabbedPane;

import main.Autowired;

public class ObjectMemberTab extends JTabbedPane {
	// private static final ObjectMemberTab objectMemberTab = new ObjectMemberTab();

	public ObjectMemberTab() {
		// this.addTab("constructor", ConstructorPanel.getInstance());
		this.addTab("field", Autowired.fieldPanel);
		this.addTab("method", Autowired.methodPanel);
	}

//	public static ObjectMemberTab getInstance() {
//		return objectMemberTab;
//	}
}
