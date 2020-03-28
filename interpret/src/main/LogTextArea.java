package main;

import static main.Autowired.*;

import javax.swing.JTextArea;

public class LogTextArea extends JTextArea implements Observer {
	private final StringBuilder logStrBuilder = new StringBuilder();

	public LogTextArea(final int i, final int j) {
		super(i, j);

		// オブジェクトの方
		constructorPrintGenerator.addObserver(this);
		constructorCreatePrintGenerator.addObserver(this);
		methodPrintGenerator.addObserver(this);
		methodExecutePrintGenerator.addObserver(this);
		fieldPrintGenerator.addObserver(this);
		fieldUpdatePrintGenerator.addObserver(this);
		// 配列の方
		setterPrintGenerator.addObserver(this);
		arrayCreatePrintGenerator.addObserver(this);
		// エラー
		errorHandler.addObserver(this);
	}

	@Override
	public void update(PrintGenerator printGenerator) {
		this.logStrBuilder.append(printGenerator.getLog());
		this.setText(this.logStrBuilder.toString());

	}
}
