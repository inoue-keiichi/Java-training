package main.view.field;

import javax.swing.JTextField;
import javax.swing.TransferHandler;

import main.generator.ErrorHandler;
import main.transfer.InstanceTransferHandler;

public class ArgumentField {
	public final JTextField text;
	private final TransferHandler transferHandler;

	public ArgumentField(final ErrorHandler errorHandler) {
		text = new JTextField(10);
		transferHandler = new InstanceTransferHandler(errorHandler, text);
		text.setTransferHandler(transferHandler);
	}
}
