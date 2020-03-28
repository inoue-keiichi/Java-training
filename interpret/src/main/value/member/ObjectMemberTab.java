package main.value.member;

import javax.swing.JTabbedPane;

import main.Autowired;

public class ObjectMemberTab extends JTabbedPane {
	public ObjectMemberTab() {
		this.addTab("field", Autowired.fieldPanel);
		this.addTab("method", Autowired.methodPanel);
	}
}
