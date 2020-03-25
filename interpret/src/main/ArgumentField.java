package main;

import javax.swing.JTextField;
import javax.swing.TransferHandler;

public class ArgumentField {
	public final JTextField text;
	private final TransferHandler transferHandler;

	public ArgumentField() {
		text = new JTextField(10);
		transferHandler = new InstanceTransferHandler(text);
		text.setTransferHandler(transferHandler);
	}
}
