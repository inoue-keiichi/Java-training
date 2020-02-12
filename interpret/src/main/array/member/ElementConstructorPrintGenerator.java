package main.array.member;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Type;

import javax.swing.JComboBox;

import main.PrintGenerator;
import main.array.ArrayReflectionService;
import static main.StringUtils.*;

public class ElementConstructorPrintGenerator extends PrintGenerator {
	private static final ElementConstructorPrintGenerator elementConstructorPrintGenerator = new ElementConstructorPrintGenerator();

	private final ElementPanel elementPanel = ElementPanel.getInstance();
	private final ArrayReflectionService reflectionService = ArrayReflectionService.getInstance();

	@Override
	public void execute() {
		final Constructor<?>[] constructors = reflectionService.getConstructor();
		final JComboBox<String> constructorComboBox = elementPanel.getConstructorComboBox();
		// 元々あった選択肢を削除
		if (constructorComboBox.getItemCount() > 0) {
			constructorComboBox.removeAllItems();
		}
		for (Constructor<?> constructor : constructors) {
			constructorComboBox.addItem(getNameAndParameter(constructor));
		}
		this.notifyObservers();
	}

	public static ElementConstructorPrintGenerator getInstance() {
		return elementConstructorPrintGenerator;
	}

	@Override
	public String getLog() {
		return "Success!\nYou can select the constructor of " + reflectionService.getClazzName() + ".\n";
	}
}