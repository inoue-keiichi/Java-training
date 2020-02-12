package main;

import main.array.member.ElementConstructorCreatePrintGenerator;
import main.array.member.ElementConstructorPrintGenerator;
import main.array.member.ElementFieldPrintGenerator;
import main.array.member.ElementFieldUpdatePrintGenerator;
import main.array.member.ElementMethodExecutePrintGenerator;
import main.array.member.ElementMethodPrintGenerator;
import main.array.type.ArrayCreatePrintGenerator;
import main.value.member.ConstructorCreatePrintGenerator;
import main.value.member.ConstructorPanel;
import main.value.member.ConstructorPrintGenerator;
import main.value.member.FieldPanel;
import main.value.member.FieldPrintGenerator;
import main.value.member.FieldUpdatePrintGenerator;
import main.value.member.MethodExecutePrintGenerator;
import main.value.member.MethodPanel;
import main.value.member.MethodPrintGenerator;

public class Main {
	public static void main(String[] args) {
		// オブジェクトの方
		final InterpretView interpretView = InterpretView.getInstance();
		final FieldPrintGenerator fieldPrintGenerator = FieldPrintGenerator.getInstance();
		final FieldUpdatePrintGenerator fieldUpdatePrintGenerator = FieldUpdatePrintGenerator.getInstance();
		final MethodPrintGenerator methodPrintGenerator = MethodPrintGenerator.getInstance();
		final MethodExecutePrintGenerator methodExecutePrintGenerator = MethodExecutePrintGenerator.getInstance();
		final ConstructorPrintGenerator constructorPrintGenerator = ConstructorPrintGenerator.getInstance();
		final ConstructorCreatePrintGenerator constructorCreatePrintGenerator = ConstructorCreatePrintGenerator
				.getInstance();
		// 配列の方
		final ElementFieldPrintGenerator elementFieldPrintGenerator = ElementFieldPrintGenerator.getInstance();
		final ElementFieldUpdatePrintGenerator elementFieldUpdatePrintGenerator = ElementFieldUpdatePrintGenerator
				.getInstance();
		final ElementMethodPrintGenerator elementMethodPrintGenerator = ElementMethodPrintGenerator.getInstance();
		final ElementMethodExecutePrintGenerator elementMethodExecutePrintGenerator = ElementMethodExecutePrintGenerator
				.getInstance();
		final ElementConstructorPrintGenerator elementConstructorPrintGenerator = ElementConstructorPrintGenerator
				.getInstance();
		final ElementConstructorCreatePrintGenerator elementConstructorCreatePrintGenerator = ElementConstructorCreatePrintGenerator
				.getInstance();
		final ArrayCreatePrintGenerator arrayCreatePrintGenerator = ArrayCreatePrintGenerator.getInstance();
		// エラー
		final ErrorHandler errorHandler = ErrorHandler.getInstance();
		// Memberタブ
		final MemberPrintGenerator memberPrintGenerator = MemberPrintGenerator.getInstance();
//		final ConstructorPanel constructorPanel = ConstructorPanel.getInstance();
//		final FieldPanel fieldPanel = FieldPanel.getInstance();
//		final MethodPanel methodPanel = MethodPanel.getInstance();
		// Observer
		final LogTextArea logTextArea = LogTextArea.getInstance();
		// オブジェクトの方
		constructorPrintGenerator.addObserver(logTextArea);
		constructorCreatePrintGenerator.addObserver(logTextArea);
		methodPrintGenerator.addObserver(logTextArea);
		methodExecutePrintGenerator.addObserver(logTextArea);
		fieldPrintGenerator.addObserver(logTextArea);
		fieldUpdatePrintGenerator.addObserver(logTextArea);
		// 配列の方
		elementFieldPrintGenerator.addObserver(logTextArea);
		elementFieldUpdatePrintGenerator.addObserver(logTextArea);
		elementMethodPrintGenerator.addObserver(logTextArea);
		elementMethodExecutePrintGenerator.addObserver(logTextArea);
		elementConstructorPrintGenerator.addObserver(logTextArea);
		elementConstructorCreatePrintGenerator.addObserver(logTextArea);
		arrayCreatePrintGenerator.addObserver(logTextArea);
		// エラー
		errorHandler.addObserver(logTextArea);
		// Memberタブ
		memberPrintGenerator.addObserver(interpretView);
	}
}
