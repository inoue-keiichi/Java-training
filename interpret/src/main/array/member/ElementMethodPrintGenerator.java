package main.array.member;

import java.lang.reflect.*;

import javax.swing.JComboBox;

import main.PrintGenerator;
import main.array.ArrayReflectionService;
import static main.StringUtils.*;

public class ElementMethodPrintGenerator extends PrintGenerator {
	private static final ElementMethodPrintGenerator methodPrintGenerator = new ElementMethodPrintGenerator();

	private final ArrayReflectionService reflectionService = ArrayReflectionService.getInstance();
	private final ElementMethodPanel memberPanel = ElementMethodPanel.getInstance();

	@Override
	public void execute() {
		// インスタンスからMethodを取得してプルダウンに表示する
		final ElementMethodPanel memberPanel = ElementMethodPanel.getInstance();
		final JComboBox<String> methodComboBox = memberPanel.getMethodCoomboBox();
		final Method[] methods = reflectionService.getNewInstance().getClass().getDeclaredMethods();
		// methodを保存
		reflectionService.setMethods(methods);
		// 元々あった選択肢を削除
		if (methodComboBox.getItemCount() != 0) {
			methodComboBox.removeAllItems();
		}
		for (Method method : methods) {
			methodComboBox.addItem(getNameAndParameter(method));
		}
		this.notifyObservers();
	}

	public static ElementMethodPrintGenerator getInstance() {
		return methodPrintGenerator;
	}

	@Override
	public String getLog() {
		if (memberPanel.getMethodCoomboBox().getItemCount() < 1) {
			return "";
		}
		return "the methods were created.\n";
	}
}
