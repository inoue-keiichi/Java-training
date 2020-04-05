package main.view.field;

import javax.swing.JTextField;
import javax.swing.TransferHandler;

import main.di.AutowiredService;
import main.generator.ErrorHandler;
import main.generator.PrintGenerator;
import main.service.ReflectionService;
import main.transfer.InstanceTransferHandler;

public class InstanceField extends PrintGenerator {
	private final ReflectionService reflectionService;

	public final JTextField text;
	private final TransferHandler transferHandler;

	public InstanceField(final ErrorHandler errorHandler, final AutowiredService service) {
		super(service);
		reflectionService = service.reflectionService;
		text = new JTextField(15);
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
