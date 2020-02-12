package main.value.member;

import java.awt.Component;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

import javax.swing.JTextField;

import main.PrintGenerator;
import main.value.ReflectionService;

public class ConstructorCreatePrintGenerator extends PrintGenerator {
	private static ConstructorCreatePrintGenerator constructorCreatePrintGenerator = new ConstructorCreatePrintGenerator();

	private final ReflectionService reflectionService = ReflectionService.getInstance();
	private final FieldPrintGenerator fieldPrintGenerator = FieldPrintGenerator.getInstance();
	private final MethodPrintGenerator methodPrintGenerator = MethodPrintGenerator.getInstance();

	private String constructorName;

	@Override
	public void execute() throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		// 入力された文字列を保存
		final ConstructorPanel constructorPanel = ConstructorPanel.getInstance();
		constructorPanel.getArgsPanel();
		int i = 0;
		for (Component comp : constructorPanel.getArgsPanel().getComponents()) {
			JTextField field = (JTextField) comp;
			reflectionService.getConstructorArgments()[i].value = field.getText();
			i++;
		}
		// 入力された文字列を引数に変換
		final Object[] args = reflectionService.validateArguments(reflectionService.getConstructorArgments());
		//try {
			// プルダウンで指定されたコンストラクタを取得してインスタンス生成
			final int index = constructorPanel.getConstructorComboBox().getSelectedIndex();
			final Object instance = reflectionService.getConstructors()[index].newInstance(args);
			reflectionService.setNewInstance(instance);
			// ログ用
			this.constructorName = reflectionService.getConstructors()[index].toGenericString();
		this.notifyObservers();
		// fieldとmethodのプルダウンの選択肢を生成
		fieldPrintGenerator.execute();
		methodPrintGenerator.execute();

	}

	public static ConstructorCreatePrintGenerator getInstance() {
		return constructorCreatePrintGenerator;
	}

	@Override
	public String getLog() {
		if (this.constructorName == null) {
			return "";
		}
		return "Success!\n" + this.constructorName + " => The instance was created.\n";
	}

}
