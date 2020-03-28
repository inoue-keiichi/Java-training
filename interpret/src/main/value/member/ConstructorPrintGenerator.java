package main.value.member;

import static main.StringUtils.*;

import java.lang.reflect.Constructor;

import javax.swing.JComboBox;

import main.Autowired;
import main.PrintGenerator;
import main.value.ReflectionService;

public class ConstructorPrintGenerator extends PrintGenerator {
	private final ReflectionService reflectionService = Autowired.reflectionService;

	@Override
	public void execute() {
		final Constructor<?>[] constructors = reflectionService.getConstructor();
		final JComboBox<String> constructorComboBox = Autowired.constructorPanel.getConstructorComboBox();
		// 元々あった選択肢を削除
		if (constructorComboBox.getItemCount() > 0) {
			constructorComboBox.removeAllItems();
		}
		for (Constructor<?> constructor : constructors) {
			constructorComboBox.addItem(getNameAndParameter(constructor));
		}
		this.notifyObservers();
	}

	@Override
	public String getLog() {
		return "Success!\nYou can select the constructor of " + reflectionService.getClazzName() + ".\n";
	}
}
