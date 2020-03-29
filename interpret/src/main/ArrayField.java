package main;

import java.lang.reflect.Array;

import javax.swing.JTextField;
import javax.swing.TransferHandler;

import main.value.ReflectionService;

public class ArrayField extends PrintGenerator {
	private final ReflectionService reflectionService;

	public final JTextField text;
	private final TransferHandler transferHandler;

	public ArrayField(final AutowiredService service) {
		super(service);
		reflectionService = service.reflectionService;
		text = new JTextField(8);
		transferHandler = new InstanceTransferHandler(this, text);
		text.setTransferHandler(transferHandler);
	}

	@Override
	public void execute() throws Throwable {
		//IndexComboBoxにindexを追加
		final Object instance = reflectionService.validateArray(text.getText());
		final int length = Array.getLength(instance);
		this.notifyObservers(length);
	}

	@Override
	public String getLog() {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}
}
