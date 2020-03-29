package main;

import javax.swing.JTextArea;
import javax.swing.text.JTextComponent;

public class LogTextArea extends View implements Observer {
	private final StringBuilder logStrBuilder = new StringBuilder();

	public LogTextArea(final AutowiredGenerator generator, AutowiredService service) {
		super(new JTextArea(10, 40), generator, service);
		// オブジェクトの方
		this.generator.constructorPrintGenerator.addObserver(this);
		this.generator.constructorCreatePrintGenerator.addObserver(this);
		this.generator.methodPrintGenerator.addObserver(this);
		this.generator.methodExecutePrintGenerator.addObserver(this);
		this.generator.fieldPrintGenerator.addObserver(this);
		this.generator.fieldUpdatePrintGenerator.addObserver(this);
		// 配列の方
		this.generator.setterPrintGenerator.addObserver(this);
		this.generator.arrayCreatePrintGenerator.addObserver(this);
		// エラー
		this.generator.errorHandler.addObserver(this);
	}

	@Override
	public void update(PrintGenerator printGenerator) {
		this.logStrBuilder.append(printGenerator.getLog());
		((JTextComponent) this.view).setText(this.logStrBuilder.toString());
	}
}
