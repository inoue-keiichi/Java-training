package main;

import javax.swing.JTextField;
import javax.swing.TransferHandler;

public class ArgumentField {
	public final JTextField text;
	private final TransferHandler transferHandler;

	public ArgumentField(final ErrorHandler errorHandler) {
		text = new JTextField(10);
		transferHandler = new InstanceTransferHandler(errorHandler, text);
		text.setTransferHandler(transferHandler);
	}
}
