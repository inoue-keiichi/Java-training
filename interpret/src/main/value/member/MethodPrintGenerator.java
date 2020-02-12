package main.value.member;

import java.lang.reflect.*;

import javax.swing.JComboBox;

import main.PrintGenerator;
import main.value.ReflectionService;
import static main.StringUtils.*;

public class MethodPrintGenerator extends PrintGenerator {
	private static final MethodPrintGenerator methodPrintGenerator = new MethodPrintGenerator();

	private final ReflectionService reflectionService = ReflectionService.getInstance();
	private final MethodPanel memberPanel = MethodPanel.getInstance();

	@Override
	public void execute() {
		// インスタンスからMethodを取得してプルダウンに表示する
		final MethodPanel memberPanel = MethodPanel.getInstance();
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

	public static MethodPrintGenerator getInstance() {
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
