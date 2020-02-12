package main;

import javax.swing.JTextArea;

public class LogTextArea extends JTextArea implements Observer {
	private static final LogTextArea logTextArea = new LogTextArea(10, 10);

	private final StringBuilder logStrBuilder = new StringBuilder();

	private LogTextArea(final int i, final int j) {
		super(i, j);
	}

	@Override
	public void update(PrintGenerator printGenerator) {
		this.logStrBuilder.append(printGenerator.getLog());
		this.setText(this.logStrBuilder.toString());

	}

	public static LogTextArea getInstance() {
		return logTextArea;
	}
}
