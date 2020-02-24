package main.value.member;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Type;

import javax.swing.JComboBox;

import main.Autowired;
import main.PrintGenerator;
import main.value.ReflectionService;
import static main.StringUtils.*;

public class ConstructorPrintGenerator extends PrintGenerator {
	// private static final ConstructorPrintGenerator constructorPrintGenerator =
	// new ConstructorPrintGenerator();

	// private final ConstructorPanel constructorPanel = Autowired.constructorPanel;
	private final ReflectionService reflectionService = ReflectionService.getInstance();

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

//	public static ConstructorPrintGenerator getInstance() {
//		return constructorPrintGenerator;
//	}

	@Override
	public String getLog() {
		return "Success!\nYou can select the constructor of " + reflectionService.getClazzName() + ".\n";
	}
}
