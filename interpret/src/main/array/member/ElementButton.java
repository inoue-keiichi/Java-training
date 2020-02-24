package main.array.member;

import javax.swing.JButton;

import main.Observer;
import main.PrintGenerator;

public class ElementButton extends JButton implements Observer {
	private static ElementButton elementButton = new ElementButton();

	public ElementButton() {
		super("     ");
	}

	@Override
	public void update(PrintGenerator printGenerator) {
		// this.setName("aaaaaa");
		this.getText();
		this.setText("aaaaa");
		this.repaint();
		this.revalidate();
	}

	public static ElementButton getInstance() {
		return elementButton;
	}

}
