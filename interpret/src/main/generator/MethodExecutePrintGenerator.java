package main.generator;

import java.awt.Component;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.swing.JTextField;

import main.di.AutowiredService;
import main.service.MemberService;
import main.service.ReflectionService;
import main.view.panel.MethodPanel;

public class MethodExecutePrintGenerator extends PrintGenerator {
	private final ReflectionService reflectionService;
	private final MemberService memberService;

	private String methodName;
	private Object returnValue;

	public MethodExecutePrintGenerator(AutowiredService servise) {
		super(servise);
		reflectionService = this.service.reflectionService;
		memberService = this.service.memberService;

	}

	@Override
	public void execute() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException,
			NoSuchFieldException, SecurityException {
		// クリア
		this.methodName = null;
		this.returnValue = null;

		final MethodPanel methodPanel = memberService.getMethodPanel();
		// メソッドの引数を保存する。
		int i = 0;
		for (Component comp : methodPanel.getArgsPanel().getComponents()) {
			JTextField field = (JTextField) comp;
			reflectionService.getMethodArgments()[i].value = field.getText();
			i++;
		}
		// メソッドを実行する。
		final Method method = reflectionService.getMethodMap().get(methodPanel.getMethodComboBox().getSelectedItem());
		final Object[] args = reflectionService.validateArguments(reflectionService.getMethodArgments());
		method.setAccessible(true);
		Object returnValue = method.invoke(memberService.getInstance(), args);
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
			return "[Success] " + this.methodName + "() was executed.\n";
		}
		return "[Success] " + this.methodName + "() => " + this.returnValue.toString() + ".\n";
	}
}
