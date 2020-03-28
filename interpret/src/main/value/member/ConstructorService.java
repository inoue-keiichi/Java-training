package main.value.member;

import javax.swing.JComboBox;

public class ConstructorService {
	private JComboBox<String> constructorComboBox;
	private ConstructorPanel constructorPanel;

	public JComboBox<String> getConstructorComboBox() {
		return constructorComboBox;
	}

	public ConstructorPanel getconstructorPanel() {
		return constructorPanel;
	}

	public void setConstructorComboBox(final JComboBox<String> constructorComboBox) {
		this.constructorComboBox = constructorComboBox;
	}

	public void setConstructorPanel(final ConstructorPanel constructorPanel) {
		this.constructorPanel = constructorPanel;
	}
}
