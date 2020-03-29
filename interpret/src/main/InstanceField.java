package main;

import javax.swing.JTextField;
import javax.swing.TransferHandler;

import main.value.ReflectionService;

public class InstanceField extends PrintGenerator {
	private final ReflectionService reflectionService;

	public final JTextField text;
	private final TransferHandler transferHandler;

	public InstanceField(final AutowiredService service) {
		super(service);
		reflectionService = service.reflectionService;
		text = new JTextField(8);
		transferHandler = new InstanceTransferHandler(this, text);
		text.setTransferHandler(transferHandler);
	}

	@Override
	public void execute() throws Throwable {
		// インスタンスが配列かオブジェクトかを知らせる
		final String str = text.getText();
		final String name = str.substring(2, str.length() - 1);

		final Object instance = reflectionService.getInstances().get(name);
		if (instance instanceof Object[]) {
			reflectionService.setInstanceType("Array");
		} else {
			reflectionService.setInstanceType("Object");
		}

		this.notifyObservers();
	}

	@Override
	public String getLog() {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}
}
