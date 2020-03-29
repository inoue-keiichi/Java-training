package main.value.member;

import main.Service;

public class ConstructorService implements Service {
	private ConstructorPanel constructorPanel;

	public ConstructorPanel getconstructorPanel() {
		return constructorPanel;
	}

	public void setConstructorPanel(final ConstructorPanel constructorPanel) {
		this.constructorPanel = constructorPanel;
	}
}
