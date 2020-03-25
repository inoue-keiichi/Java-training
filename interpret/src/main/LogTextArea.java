package main;

import javax.swing.JTextArea;

public class LogTextArea extends JTextArea implements Observer {
	private final StringBuilder logStrBuilder = new StringBuilder();

	public LogTextArea(final int i, final int j) {
		super(i, j);
	}

	@Override
	public void update(PrintGenerator printGenerator) {
		this.logStrBuilder.append(printGenerator.getLog());
		this.setText(this.logStrBuilder.toString());

	}
}
