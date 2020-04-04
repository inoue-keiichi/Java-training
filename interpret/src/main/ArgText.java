package main;

import javax.swing.JTextField;
import javax.swing.TransferHandler;

public class ArgText {
	public final JTextField text;
	private final TransferHandler transferHandler;

	public ArgText(final ErrorHandler errorHandler) {
		text = new JTextField(10);
		transferHandler = new InstanceTransferHandler(errorHandler, text);
		text.setTransferHandler(transferHandler);
	}

}
