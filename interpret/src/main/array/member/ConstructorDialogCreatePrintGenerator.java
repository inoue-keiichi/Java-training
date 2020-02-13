package main.array.member;

import java.awt.Component;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

import javax.swing.JTextField;

import main.PrintGenerator;
import main.array.ArrayReflectionService;

public class ElementConstructorCreatePrintGenerator extends PrintGenerator {
	private static ElementConstructorCreatePrintGenerator elementConstructorCreatePrintGenerator = new ElementConstructorCreatePrintGenerator();

	private final ArrayReflectionService reflectionService = ArrayReflectionService.getInstance();
	private final ElementFieldPrintGenerator fieldPrintGenerator = ElementFieldPrintGenerator.getInstance();
	private final ElementMethodPrintGenerator methodPrintGenerator = ElementMethodPrintGenerator.getInstance();

	private String constructorName;

	@Override
	public void execute()
			throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		// 入力された文字列を保存
		final ElementPanel elementPanel = ElementPanel.getInstance();
		elementPanel.getArgsPanel();
		int i = 0;
		for (Component comp : elementPanel.getArgsPanel().getComponents()) {
			JTextField field = (JTextField) comp;
			reflectionService.getConstructorArgments()[i].value = field.getText();
			i++;
		}
		// 入力された文字列を引数に変換
		final Object[] args = reflectionService.validateArguments(reflectionService.getConstructorArgments());
		// try {
		// プルダウンで指定されたコンストラクタを取得してインスタンス生成
		final int index = elementPanel.getConstructorComboBox().getSelectedIndex();
		final Object instance = reflectionService.getConstructors()[index].newInstance(args);
		reflectionService.setNewInstance(instance);
		// ログ用
		this.constructorName = reflectionService.getConstructors()[index].toGenericString();
		this.notifyObservers();
		// fieldとmethodのプルダウンの選択肢を生成
		fieldPrintGenerator.execute();
		methodPrintGenerator.execute();

	}

	public static ElementConstructorCreatePrintGenerator getInstance() {
		return elementConstructorCreatePrintGenerator;
	}

	@Override
	public String getLog() {
		if (this.constructorName == null) {
			return "";
		}
		return "Success!\n" + this.constructorName + " => The instance was created.\n";
	}

}
