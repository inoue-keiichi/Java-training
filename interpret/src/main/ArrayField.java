package main;

import java.lang.reflect.Array;

import javax.swing.JTextField;
import javax.swing.TransferHandler;

import main.array.member.SetterClearPrintGenerator;
import main.value.ReflectionService;

public class ArrayField extends PrintGenerator {
	private final ReflectionService reflectionService;
	private final ErrorHandler errorHandler;
	private final SetterClearPrintGenerator setterClearPrintGenerator;

	public final JTextField text;
	private final TransferHandler transferHandler;

	public ArrayField(final AutowiredGenerator generator, final AutowiredService service) {
		super(service);
		this.reflectionService = service.reflectionService;
		this.errorHandler = generator.errorHandler;
		this.setterClearPrintGenerator = generator.setterClearPrintGenerator;
		text = new JTextField(8);
		transferHandler = new InstanceTransferHandler(errorHandler, this, text);
		text.setTransferHandler(transferHandler);
	}

	@Override
	public void execute() throws Throwable {
		//IndexComboBoxにindexを追加
		try {
			final Object instance = reflectionService.validateArray(text.getText());
			final int length = Array.getLength(instance);
			this.notifyObservers(length);
		} catch (Exception e) {
			this.setterClearPrintGenerator.execute();
			this.errorHandler.execute(e);
		}
	}

	@Override
	public String getLog() {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}
}
