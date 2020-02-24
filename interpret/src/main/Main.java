package main;

import main.array.member.ConstructorDialogCreatePrintGenerator;
import main.array.member.ConstructorDialogPrintGenerator;
import main.array.member.ElementButton;
import main.array.member.ElementButtonPrintGenerator;
import main.array.member.ElementFieldPrintGenerator;
import main.array.member.ElementFieldUpdatePrintGenerator;
import main.array.member.ElementMethodExecutePrintGenerator;
import main.array.member.ElementMethodPrintGenerator;
import main.array.member.SetterPrintGenerator;
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
import static main.Autowired.*;

public class Main {
	public static void main(String[] args) {
		// オブジェクトの方
		final InterpretView interpretView = InterpretView.getInstance();
		// final FieldPrintGenerator fieldPrintGenerator =
		// FieldPrintGenerator.getInstance();
		// final FieldUpdatePrintGenerator fieldUpdatePrintGenerator =
		// FieldUpdatePrintGenerator.getInstance();
		// final MethodPrintGenerator methodPrintGenerator =
		// MethodPrintGenerator.getInstance();
		// final MethodExecutePrintGenerator methodExecutePrintGenerator =
		// methodExecutePrintGenerator;
		// final ConstructorPrintGenerator constructorPrintGenerator =
		// ConstructorPrintGenerator.getInstance();
//		final ConstructorCreatePrintGenerator constructorCreatePrintGenerator = ConstructorCreatePrintGenerator
//				.getInstance();
		// 配列の方
		final ElementFieldPrintGenerator elementFieldPrintGenerator = ElementFieldPrintGenerator.getInstance();
		final ElementFieldUpdatePrintGenerator elementFieldUpdatePrintGenerator = ElementFieldUpdatePrintGenerator
				.getInstance();
		final ElementMethodPrintGenerator elementMethodPrintGenerator = ElementMethodPrintGenerator.getInstance();
		final ElementMethodExecutePrintGenerator elementMethodExecutePrintGenerator = ElementMethodExecutePrintGenerator
				.getInstance();
		final ConstructorDialogPrintGenerator constructorDialogPrintGenerator = ConstructorDialogPrintGenerator
				.getInstance();
		final ConstructorDialogCreatePrintGenerator constructorDialogCreatePrintGenerator = ConstructorDialogCreatePrintGenerator
				.getInstance();
		final ElementButtonPrintGenerator elementButtonPrintGenerator = ElementButtonPrintGenerator.getInstance();
		final SetterPrintGenerator setterPrintGenerator = SetterPrintGenerator.getInstance();
		final ArrayCreatePrintGenerator arrayCreatePrintGenerator = ArrayCreatePrintGenerator.getInstance();
		// エラー
		final ErrorHandler errorHandler = ErrorHandler.getInstance();
		// Memberタブ
		// final MemberPrintGenerator memberPrintGenerator =
		// MemberPrintGenerator.getInstance();
		// Observer
		final LogTextArea logTextArea = LogTextArea.getInstance();
		final ElementButton elementButton = ElementButton.getInstance();
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
		constructorDialogPrintGenerator.addObserver(logTextArea);
		constructorDialogCreatePrintGenerator.addObserver(logTextArea);
		elementButtonPrintGenerator.addObserver(elementButton);
		setterPrintGenerator.addObserver(logTextArea);
		arrayCreatePrintGenerator.addObserver(logTextArea);
		// エラー
		errorHandler.addObserver(logTextArea);
		// Memberタブ
		// memberPrintGenerator.addObserver(interpretView);
	}
}
