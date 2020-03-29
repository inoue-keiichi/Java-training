package main.value.member;

import java.awt.Component;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.swing.JTextField;

import main.Autowired;
import main.PrintGenerator;
import main.value.ReflectionService;

public class MethodExecutePrintGenerator extends PrintGenerator {
	private static final ReflectionService reflectionService = Autowired.reflectionService;

	private String methodName;
	private Object returnValue;

	@Override
	public void execute() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException,
			NoSuchFieldException, SecurityException {
		// クリア
		this.methodName = null;
		this.returnValue = null;

		final MethodPanel methodPanel = Autowired.memberService.getMethodPanel();
		// メソッドの引数を保存する。
		int i = 0;
		for (Component comp : methodPanel.getArgsPanel().getComponents()) {
			JTextField field = (JTextField) comp;
			reflectionService.getMethodArgments()[i].value = field.getText();
			i++;
		}
		// メソッドを実行する。
		final Method method = reflectionService.getMethods()[methodPanel.getMethodComboBox().getSelectedIndex()];
		final Object[] args = reflectionService.validateArguments(reflectionService.getMethodArgments());
		method.setAccessible(true);
		Object returnValue = method.invoke(reflectionService.getNewInstance(), args);
		// ログ用
		this.methodName = method.getName();
		this.returnValue = returnValue;
		// アクセス制限
		method.setAccessible(false);
		this.notifyObservers();
	}

	@Override
	public String getLog() {
		if (this.methodName == null) {
			return "";
		} else if (this.methodName != null && this.returnValue == null) {
			// returnがvoid
			return "Success!\n" + this.methodName + "() was executed.\n";
		}
		return "Success!\n" + this.methodName + "() => " + this.returnValue.toString() + ".\n";
	}
}
