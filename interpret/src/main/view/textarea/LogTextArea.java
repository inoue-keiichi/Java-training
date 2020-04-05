package main.view.textarea;

import javax.swing.JTextArea;
import javax.swing.text.JTextComponent;

import main.di.AutowiredGenerator;
import main.di.AutowiredService;
import main.generator.PrintGenerator;
import main.view.Observer;
import main.view.View;

public class LogTextArea extends View implements Observer {
	private final StringBuilder logStrBuilder = new StringBuilder();

	public LogTextArea(final AutowiredGenerator generator, AutowiredService service) {
		super(new JTextArea(10, 50), generator, service);
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
