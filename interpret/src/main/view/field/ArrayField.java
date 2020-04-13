package main.view.field;

import java.lang.reflect.Array;

import javax.swing.JTextField;
import javax.swing.TransferHandler;

import main.di.AutowiredGenerator;
import main.di.AutowiredService;
import main.generator.ErrorHandler;
import main.generator.PrintGenerator;
import main.generator.SetterClearPrintGenerator;
import main.service.ReflectionService;
import main.transfer.InstanceTransferHandler;

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
		text = new JTextField(15);
		transferHandler = new InstanceTransferHandler(this, text);
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
