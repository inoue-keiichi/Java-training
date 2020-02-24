package main.array.member;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Type;

import javax.swing.JComboBox;

import main.PrintGenerator;
import static main.StringUtils.*;

public class ConstructorDialogPrintGenerator extends PrintGenerator {
	private static final ConstructorDialogPrintGenerator constructorDialogPrintGenerator = new ConstructorDialogPrintGenerator();

	private final ConstructorDialog constructorDialog = ConstructorDialog.getInstance();
	// private final ArrayReflectionService reflectionService =
	// ArrayReflectionService.getInstance();

	@Override
	public void execute() {
//		final Constructor<?>[] constructors = reflectionService.getConstructor();
//		final JComboBox<String> constructorComboBox = constructorDialog.getConstructorComboBox();
//		// 元々あった選択肢を削除
//		if (constructorComboBox.getItemCount() > 0) {
//			constructorComboBox.removeAllItems();
//		}
//		for (Constructor<?> constructor : constructors) {
//			constructorComboBox.addItem(getNameAndParameter(constructor));
//		}
//		this.notifyObservers();
	}

	public static ConstructorDialogPrintGenerator getInstance() {
		return constructorDialogPrintGenerator;
	}

	@Override
	public String getLog() {
		// return "Success!\nYou can select the constructor of " +
		// reflectionService.getClazzName() + ".\n";
		return null;
	}
}