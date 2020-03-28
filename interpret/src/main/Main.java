package main;

import static main.Autowired.*;

//import main.array.member.ElementButtonPrintGenerator;
import main.array.member.SetterPrintGenerator;
import main.array.type.ArrayCreatePrintGenerator;

public class Main {
	public static void main(String[] args) {
		// オブジェクトの方
		final InterpretView interpretView = new InterpretView();
		// 配列の方
		//final ElementButtonPrintGenerator elementButtonPrintGenerator = ElementButtonPrintGenerator.getInstance();
		final SetterPrintGenerator setterPrintGenerator = SetterPrintGenerator.getInstance();
		final ArrayCreatePrintGenerator arrayCreatePrintGenerator = ArrayCreatePrintGenerator.getInstance();
		// エラー
		final ErrorHandler errorHandler = ErrorHandler.getInstance();
		// Memberタブ
		final Observer logTextArea = (Observer) interpretView.logTextArea;
		//final ElementButton elementButton = ElementButton.getInstance();
		// オブジェクトの方
		constructorPrintGenerator.addObserver(logTextArea);
		constructorCreatePrintGenerator.addObserver(logTextArea);
		methodPrintGenerator.addObserver(logTextArea);
		methodExecutePrintGenerator.addObserver(logTextArea);
		fieldPrintGenerator.addObserver(logTextArea);
		fieldUpdatePrintGenerator.addObserver(logTextArea);
		// 配列の方
		//elementButtonPrintGenerator.addObserver(elementButton);
		setterPrintGenerator.addObserver(logTextArea);
		arrayCreatePrintGenerator.addObserver(logTextArea);
		// エラー
		errorHandler.addObserver(logTextArea);
	}
}
