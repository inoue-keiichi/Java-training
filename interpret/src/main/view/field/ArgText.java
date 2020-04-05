package main.view.field;

import javax.swing.JTextField;
import javax.swing.TransferHandler;

import main.generator.ErrorHandler;
import main.transfer.InstanceTransferHandler;

public class ArgText {
	public final JTextField text;
	private final TransferHandler transferHandler;

	public ArgText(final ErrorHandler errorHandler) {
		text = new JTextField(10);
		transferHandler = new InstanceTransferHandler(errorHandler, text);
		text.setTransferHandler(transferHandler);
	}

}
