package main;

import javax.swing.JButton;

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
