package main.view.button;

import javax.swing.JButton;

import main.generator.PrintGenerator;
import main.view.Observer;

public class ObserverButton implements Observer {
	public final JButton btn;

	public ObserverButton(final String name) {
		this.btn = new JButton(name);
		this.btn.setEnabled(false);
	}

	@Override
	public void update(PrintGenerator printGenerator) {
		this.btn.setEnabled(true);
	}

}
