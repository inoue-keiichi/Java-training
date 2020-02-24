package main.array.member;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

import main.AbstractButton;
import main.ErrorHandler;
import main.array.ArrayReflectionService;

public class TypeSetListener implements ActionListener {

	private final ArrayReflectionService reflectionService = ArrayReflectionService.getInstance();
	private final ErrorHandler errorHandler = ErrorHandler.getInstance();

	@Override
	public void actionPerformed(ActionEvent e) {
		final ConstructorDialog constructorDialog = ConstructorDialog.getInstance();
		final ConstructorDialogPrintGenerator constructorDialogPrintGenerator = ConstructorDialogPrintGenerator
				.getInstance();
		try {
			reflectionService.setClazz(constructorDialog.getTypeText().getText());
		} catch (ClassNotFoundException e1) {
			errorHandler.execute(e1);
		}
		// コンストラクタの項目を表示する
		constructorDialogPrintGenerator.execute();
//		// メンバータブを表示する
//		try {
//			memberPrintGenerator.execute();
//		} catch (Throwable e1) {
//			errorHandler.execute(e1);
//		}
	}
}